package com.file.transfer.domain.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.file.transfer.domain.file.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long>, FileRepositoryCustom{

}
