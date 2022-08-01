package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TransactionRowMapper implements RowMapper<Transaction>{

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setId(rs.getLong("id"));
		transaction.setAccountNo(rs.getString("account_no"));
		transaction.setTransferYn(rs.getString("transfer_yn"));
		transaction.setAmmount(rs.getBigDecimal("ammount"));
		transaction.setTransferDate(rs.getString("trans_date"));
		return transaction;
	}

}
