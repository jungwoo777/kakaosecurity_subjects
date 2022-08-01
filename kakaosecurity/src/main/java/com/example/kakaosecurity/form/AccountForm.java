package com.example.kakaosecurity.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "계좌정보")
@Getter
@Setter
public class AccountForm {
	
	@Schema(description = "계좌번호", nullable = false, example = "1000-51")
	private String accountNo;
	
	@Schema(description = "고객ID", nullable = false, example = "21")
	private int customerId;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	

}
