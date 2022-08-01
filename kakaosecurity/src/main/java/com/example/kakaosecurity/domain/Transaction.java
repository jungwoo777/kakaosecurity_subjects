package com.example.kakaosecurity.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transaction {
	
	private long id;
	private String accountNo;
	private String transferYn;
	private BigDecimal ammount;
	private String transferDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
