package com.file.transfer.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	//User
	ROOM_UNKNOWN("ROO0001", "해당 채팅방이 존재하지않습니다. 관리자에게 문의 해주세요"),
	
	ALREADY_EXIST("ARE0001", "이미 등록된 사용자입니다."),
	
	USER_UNKNOWN("USE0001", "존재하지 않는 사용자입니다.");
	
	private String code;
	private String message;
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
	
	
	
