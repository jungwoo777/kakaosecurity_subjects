package com.example.kakaosecurity.domain;

import lombok.Data;


/**
 * 고객 객체
 * @author jungwoo
 *
 */
@Data
public class Customer {
	
	private long id;
	private int customerId;
	private String name;
	private int age;
	private String enterDate;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
