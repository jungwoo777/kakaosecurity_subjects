package com.example.kakaosecurity.response;

public class ApiErrorException extends RuntimeException {
    private ApiErrorEnum errorEnum;
    private String item;

    public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public ApiErrorException(ApiErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
    
    public ApiErrorException(ApiErrorEnum errorEnum, String item) {
    	this.errorEnum = errorEnum;
    }

    public ApiErrorEnum getErrorEnum() {
        return this.errorEnum;
    }

    public void setId(ApiErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
}