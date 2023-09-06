package com.file.transfer.domain.file.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDto {
	
	private MultipartFile file;
	private String password;

}
