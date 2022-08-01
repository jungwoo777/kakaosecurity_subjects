package com.example.kakaosecurity.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.kakaosecurity.domain.Customer;
import com.example.kakaosecurity.domain.CustomerRowMapper;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	private final JdbcTemplate jdbcTemplate;
	private final CustomerRowMapper customerRowMapper;
	
	@Autowired
    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = new CustomerRowMapper();
    }

	@Override
	public Customer save(Customer customer) {
		
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("customer").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("customer_id", customer.getCustomerId());
        parameters.put("name", customer.getName());
        parameters.put("age", customer.getAge());
        parameters.put("enter_date", customer.getEnterDate());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        customer.setId(key.longValue());

        return customer;
	}

	@Override
	public List<Customer> findAll() {
		return jdbcTemplate.query("select * from customer", customerRowMapper);
	}

	@Override
	public Optional<Customer> findByCustomerId(int customerId) {
		List<Customer> result = jdbcTemplate.query("select * from customer where customer_id = ?", customerRowMapper, customerId);
        return result.stream().findAny();
	}

}
