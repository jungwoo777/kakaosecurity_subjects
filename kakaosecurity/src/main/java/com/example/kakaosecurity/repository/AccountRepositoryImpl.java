package com.example.kakaosecurity.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.kakaosecurity.domain.Account;
import com.example.kakaosecurity.domain.AccountRowMapper;
import com.example.kakaosecurity.domain.Customer;


/**
 * 계좌 repository
 * @author jungwoo
 *
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private final AccountRowMapper accountRowMapper;
	
	public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		accountRowMapper = new AccountRowMapper();
	}

	@Override
	public Account save(Account account) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("account").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account_no", account.getAccountNo());
        parameters.put("customer_id", account.getCustomerId());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        account.setId(key.longValue());

        return account;
	}

	@Override
	public List<Account> findAll() {
		return jdbcTemplate.query("select * from account", accountRowMapper);
	}

	@Override
	public Optional<Account> findByAccountNo(String accountNo) {
		List<Account> result = jdbcTemplate.query("select * from account where account_no= ?", accountRowMapper, accountNo);
        return result.stream().findAny();
	}

}
