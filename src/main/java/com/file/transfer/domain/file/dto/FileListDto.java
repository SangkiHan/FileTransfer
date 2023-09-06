package com.file.transfer.domain.file.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class FileListDto {
	
	private Long id;
	private String originalName;
	private String createdDate;
	
	public FileListDto(Long id, String originalName, LocalDateTime createdDate) {
		this.id = id;
		this.originalName = originalName;
		this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss"));
	}
}
