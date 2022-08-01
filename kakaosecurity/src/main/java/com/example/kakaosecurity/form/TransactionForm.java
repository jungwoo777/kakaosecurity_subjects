package com.example.kakaosecurity.form;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema
@Getter
@Setter
public class TransactionForm {
	
	@Schema(description = "계좌번호", nullable = false, example = "1000-01")
	private String accountNo;
	
	@Schema(description = "입금여부", nullable = false, example = "Y")
	private String transferYn;
	
	@Schema(description = "금액", nullable = false, example = "12345", maxLength = 9)
	private BigDecimal ammount;
	
	@Schema(description = "거래일자", example = "2022-08-01", maxLength = 10)
	private String transferDate;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getTransferYn() {
		return transferYn;
	}
	public void setTransferYn(String transferYn) {
		this.transferYn = transferYn;
	}
	public BigDecimal getAmmount() {
		return ammount;
	}
	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	
	

}
