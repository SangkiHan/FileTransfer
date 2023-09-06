package com.file.transfer.domain.file.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileCheckDto {
	
	private Long id;
	private String password;
	
	public FileCheckDto(Long id, String password) {
		this.id = id;
		this.password = password;
	}
}
