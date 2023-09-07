package com.file.transfer.domain.file.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.transfer.domain.file.dto.FileCheckDto;
import com.file.transfer.domain.file.dto.FileDto;
import com.file.transfer.domain.file.dto.FileListDto;
import com.file.transfer.domain.file.service.FileService;
import com.file.transfer.domain.text.service.TextService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FileController {
	
	private final FileService fileService;
	private final TextService textService;
	
	@GetMapping("/")
	public String home(Model model) {
        List<FileListDto> fileListDtoList = fileService.findList();
        model.addAttribute("fileListDtoList", fileListDtoList);
        model.addAttribute("text", textService.findText());   
		return "home";
	}
	
	@PostMapping("/upload")
    public String uploadFile(@ModelAttribute("fileDto") FileDto fileDto) throws IOException {
		
        fileService.saveFile(fileDto);

        return "redirect:/";
    }
	
	@PostMapping("/download/{id}")
	@ResponseBody
    public void findList(@PathVariable Long id, String filePassword, HttpServletResponse response){
		fileService.download(id, filePassword, response);
    }
	
	@PostMapping("/checkPassword")
	@ResponseBody
	public void checkPassword(@RequestBody FileCheckDto request) {
		fileService.checkPassword(request);
	}

}
