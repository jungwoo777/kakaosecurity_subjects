package com.example.kakaosecurity.response;

import org.springframework.http.HttpStatus;

public enum ApiErrorEnum {
    NO_ELEMENT(HttpStatus.NO_CONTENT, "E0001", "조회 대상이 업습니다."), 
    INSERT_ERROR_ACCOUNT(HttpStatus.NOT_ACCEPTABLE, "E0002", "입력하신 계좌정보가 존재하지 않습니다."), 
    INSERT_ERROR_CUSTOMER(HttpStatus.NOT_ACCEPTABLE, "E0003", "입력하신 고객정보가 존재하지 않습니다."),
    DUPLICATED_ACCOUNT(HttpStatus.LOCKED, "E0004", "입력하신 계좌번호가 존재합니다."),
    DUPLICATED_CUSTOMER(HttpStatus.LOCKED, "E0005", "입력하신 고객ID가 존재합니다."),
    BAD_INPUT_FORMAT_DATE(HttpStatus.BAD_REQUEST, "E0006", "날짜 입력형식이 잘못되었습니다. 다음의 형식으로 입력해주세요. yyyy-mm-dd"),
    ;
    
    private final HttpStatus status;
    private final String code;
    private String message;
    
    ApiErrorEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ApiErrorEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getCode() {
		return code;
	}
    
    

}
