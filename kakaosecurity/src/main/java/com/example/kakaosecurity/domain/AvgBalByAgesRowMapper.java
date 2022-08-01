package com.example.kakaosecurity.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AvgBalByAgesRowMapper implements RowMapper<AvgBalByAges>{

	@Override
	public AvgBalByAges mapRow(ResultSet rs, int rowNum) throws SQLException {
		AvgBalByAges avgBalByAges = new AvgBalByAges();
		avgBalByAges.setAges(rs.getInt("ages"));
		avgBalByAges.setAvgAmmount(rs.getDouble("avg_ammount"));
		return avgBalByAges;
	}

}
