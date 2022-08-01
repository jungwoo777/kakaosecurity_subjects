package com.example.kakaosecurity.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description="고객")
@Getter
@Setter
public class CustomerForm {
	
	@Schema(description = "고객ID", nullable = false, example = "21")
	private int customerId;
	
	@Schema(description = "고객명", nullable = false, example = "홍길동")
	private String name;
	
	@Schema(description = "나이", example = "21")
	private int age;
	
	@Schema(description = "가입일자", example = "2022-08-01", maxLength = 10)
	private String enterDate;
	
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	
	

}
