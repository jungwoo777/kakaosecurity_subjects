package com.example.kakaosecurity.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.kakaosecurity.domain.Transaction;
import com.example.kakaosecurity.domain.TransactionRowMapper;


/**
 * 거래정보 repository
 * @author jungwoo
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private final TransactionRowMapper transactionRowMapper;
	
	@Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionRowMapper = new TransactionRowMapper();
    }

	@Override
	public Transaction save(Transaction transaction) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("transaction").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account_no", transaction.getAccountNo());
        parameters.put("transfer_yn", transaction.getTransferYn());
        parameters.put("ammount", transaction.getAmmount());
        parameters.put("trans_date", transaction.getTransferDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        transaction.setId(key.longValue());

        return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		return jdbcTemplate.query("select * from transaction", transactionRowMapper);
	}


}
