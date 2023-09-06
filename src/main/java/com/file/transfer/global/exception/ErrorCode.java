package com.file.transfer.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	//AES
	ENCRYPT_ERROR("ENC0001", "암호화 중 에러가 발생하였습니다."),
	DECRYPT_ERROR("DEC0001", "복호화 중 에러가 발생하였습니다."),
	
	//File
	FILE_NOT_EXIST("FIL0001", "파일이 존재하지 않습니다."),
	FILE_EXCEPTION("FIL0002", "파일 처리 도중 에러가 발생했습니다."),
	
	PASSWORD_NOT("PAS0001", "패스워드가 일치하지 않습니다.");
	
	private String code;
	private String message;
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
	
	
	
