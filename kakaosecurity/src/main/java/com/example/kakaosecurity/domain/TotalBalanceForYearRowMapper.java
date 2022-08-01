package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TotalBalanceForYearRowMapper implements RowMapper<TotalBalForYear>{

	@Override
	public TotalBalForYear mapRow(ResultSet rs, int rowNum) throws SQLException {
		TotalBalForYear totalBalForYear = new TotalBalForYear();
		totalBalForYear.setTotalBalance(rs.getBigDecimal("total_balance"));
		return totalBalForYear;
	}

}
