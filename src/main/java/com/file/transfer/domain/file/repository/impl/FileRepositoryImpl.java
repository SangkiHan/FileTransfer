package com.file.transfer.domain.file.repository.impl;

import static com.file.transfer.domain.file.entity.QFileEntity.fileEntity;

import java.util.List;

import com.file.transfer.domain.file.dto.FileListDto;
import com.file.transfer.domain.file.repository.FileRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileRepositoryImpl implements FileRepositoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public List<FileListDto> findList() {
		
		List<FileListDto> results = queryFactory
				.select(Projections.constructor(FileListDto.class,
						fileEntity.id,
						fileEntity.originalName,
						fileEntity.createdDate
						))
				.from(fileEntity)
				.orderBy(fileEntity.createdDate.desc())
				.fetch();
		
		return results;
	}

	@Override
	public String findPassword(Long id) {
		return queryFactory
				.select(
						fileEntity.password
						)
				.from(fileEntity)
				.where(fileEntity.id.eq(id))
				.fetchOne();
	}

}
