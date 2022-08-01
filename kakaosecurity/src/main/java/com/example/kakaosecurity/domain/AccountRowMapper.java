package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getLong("id"));
		account.setAccountNo(rs.getString("account_no"));
		account.setCustomerId(rs.getInt("customer_id"));
		return account;
	}

}
