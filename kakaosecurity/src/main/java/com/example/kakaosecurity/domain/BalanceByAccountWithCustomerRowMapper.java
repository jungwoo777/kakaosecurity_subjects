package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BalanceByAccountWithCustomerRowMapper implements RowMapper<BalanceByAccountWithCustomer>{

	@Override
	public BalanceByAccountWithCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
		BalanceByAccountWithCustomer balanceByAccountWithCustomer = new BalanceByAccountWithCustomer();
		balanceByAccountWithCustomer.setCustomerId(rs.getInt("customer_id"));
		balanceByAccountWithCustomer.setAccount(rs.getString("account_no"));
		balanceByAccountWithCustomer.setTotalAmount(rs.getBigDecimal("total_ammount"));
		
		return balanceByAccountWithCustomer;
	}

}
