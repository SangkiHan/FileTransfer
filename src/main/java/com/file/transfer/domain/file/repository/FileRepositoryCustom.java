package com.file.transfer.domain.file.repository;

import java.util.List;

import com.file.transfer.domain.file.dto.FileListDto;

public interface FileRepositoryCustom {
	
	List<FileListDto> findList();
	String findPassword(Long id);

}
