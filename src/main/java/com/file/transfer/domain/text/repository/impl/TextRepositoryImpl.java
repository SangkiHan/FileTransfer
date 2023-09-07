package com.file.transfer.domain.text.repository.impl;

import static com.file.transfer.domain.text.entity.QText.text1;

import com.file.transfer.domain.text.dto.TextDto;
import com.file.transfer.domain.text.repository.TextRespostoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextRepositoryImpl implements TextRespostoryCustom{
	
	private final JPAQueryFactory queryFactory;

	@Override
	public TextDto findText() {
		return queryFactory
				.select(Projections.constructor(TextDto.class, 
						text1.id,
						text1.text
						))
				.from(text1)
				.limit(1)
				.fetchOne();
	}

}
