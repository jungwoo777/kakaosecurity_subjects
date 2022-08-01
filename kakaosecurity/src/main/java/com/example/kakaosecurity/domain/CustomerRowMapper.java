package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getLong("id"));
		customer.setCustomerId(rs.getInt("customer_id"));
		customer.setName(rs.getString("name"));
		customer.setAge(rs.getInt("age"));
		customer.setEnterDate(rs.getString("enter_date"));
		return customer;
	}

}
