package com.file.transfer.domain.file.service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.transfer.domain.file.dto.FileCheckDto;
import com.file.transfer.domain.file.dto.FileDto;
import com.file.transfer.domain.file.dto.FileInfoDto;
import com.file.transfer.domain.file.dto.FileListDto;
import com.file.transfer.domain.file.entity.FileEntity;
import com.file.transfer.domain.file.repository.FileRepository;
import com.file.transfer.global.exception.ErrorCode;
import com.file.transfer.global.exception.GlobalException;
import com.file.transfer.global.util.AESUtil;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class FileService {
	
	@Value("${file.dir}")
    private String fileDir;

    private final FileRepository fileRepository;

    public Long saveFile(FileDto request) throws IOException {
    	
    	MultipartFile files = request.getFile();
    	
        if (files.isEmpty()) {
           throw new GlobalException(ErrorCode.FILE_NOT_EXIST);
        }

        String originalName = files.getOriginalFilename(); 							// 원래 파일 이름 추출
        String uuid = UUID.randomUUID().toString();									// 파일 이름으로 쓸 uuid 생성
        String extension = originalName.substring(originalName.lastIndexOf("."));	// 확장자 추출(ex : .png)
        String fileName = uuid + extension;											// uuid와 확장자 결합
        String filePath = fileDir + fileName;										// 파일을 불러올 때 사용할 파일 경로

        // 파일 엔티티 생성
        FileEntity file = FileEntity.builder()
                .originalName(originalName)
                .fileName(fileName)
                .filePath(filePath)
                .build();
        
        file.setPassword(request.getPassword());

        // 실제로 로컬에 uuid를 파일명으로 저장
        files.transferTo(new File(filePath));

        // 데이터베이스에 파일 정보 저장 
        FileEntity savedFile = fileRepository.save(file);

        return savedFile.getId();
    }
    
    public void download(Long id, String password, HttpServletResponse response) {
    	FileCheckDto fileInfo = new FileCheckDto(id, password);
    	checkPassword(fileInfo);
    	
    	FileEntity file = fileRepository.findById(id)
    			.orElseThrow(() -> new GlobalException(ErrorCode.FILE_NOT_EXIST));
    	
    	FileInfoDto fileDto = new FileInfoDto(file);
    	
    	File fileData = new File(fileDto.getFilePath());
    	
    	try {
    		byte[] fileByte = FileUtils.readFileToByteArray(fileData);
    		String downloadFilename = URLEncoder.encode(fileDto.getOriginalName(),"UTF-8");
    		
    		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+downloadFilename);
    		response.setHeader(HttpHeaders.CONTENT_TYPE,  "application/octet-stream; charset=utf-8");
    		response.setHeader("Content-Transfer-Encoding","binary");
    		
    		response.getOutputStream().write(fileByte);
    	    response.getOutputStream().flush();
    	    response.getOutputStream().close();
    	    
		} catch (Exception e) {
			throw new GlobalException(ErrorCode.FILE_EXCEPTION);
		} finally {
			fileData.delete();
			fileRepository.deleteById(id);
		}
    }
    
    public List<FileListDto> findList(){
    	
    	return fileRepository.findList();
    }
    
    public void checkPassword(FileCheckDto request) {
    	String password = fileRepository.findPassword(request.getId());
    	if(!AESUtil.decrypt(password).equals(request.getPassword())) {
    		throw new GlobalException(ErrorCode.PASSWORD_NOT);
    	}
    }
}
