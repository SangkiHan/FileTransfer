package com.file.transfer.domain.file.dto;

import com.file.transfer.domain.file.entity.FileEntity;

import lombok.Data;

@Data
public class FileInfoDto {
	private Long id;
	    
    private String originalName;
    
    private String fileName;
    
    private String filePath;
    
    private String password;

	public FileInfoDto(FileEntity fileEntity) {
		this.id = fileEntity.getId();
		this.originalName = fileEntity.getOriginalName();
		this.fileName = fileEntity.getFileName();
		this.filePath = fileEntity.getFilePath();
		this.password = fileEntity.getPassword();
	}
}
