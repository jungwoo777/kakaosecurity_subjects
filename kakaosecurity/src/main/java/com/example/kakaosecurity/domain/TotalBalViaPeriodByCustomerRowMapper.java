package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TotalBalViaPeriodByCustomerRowMapper implements RowMapper<TotalBalViaPeriodByCustomer>{

	@Override
	public TotalBalViaPeriodByCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
		TotalBalViaPeriodByCustomer out = new TotalBalViaPeriodByCustomer();
		out.setCustomerId(rs.getInt("customer_id"));
		out.setName(rs.getString("name"));
		out.setTotalBalance(rs.getBigDecimal("total_balance"));
		
		return out;
	}

}
