package com.example.kakaosecurity.domain;

import java.math.BigDecimal;


/**
 * 계좌별 총예치금 객체
 * @author jungwoo
 *
 */
public class BalanceByAccountWithCustomer {
	
	private int customerId;
	private String account;
	private BigDecimal totalAmount;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	

}
