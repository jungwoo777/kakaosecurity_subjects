package com.example.kakaosecurity.domain;

import java.math.BigDecimal;


/**
 * 기간별 총예치금 객체
 * @author jungwoo
 *
 */
public class TotalBalViaPeriodByCustomer {
	
	private int customerId;
	private String name;
	private BigDecimal totalBalance;
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
	public BigDecimal getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	

}
