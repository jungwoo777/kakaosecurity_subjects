package com.example.kakaosecurity.repository;

import java.util.List;
import java.util.Optional;

import com.example.kakaosecurity.domain.Customer;

public interface CustomerRepository {
	
	Customer save(Customer customer);
    List<Customer> findAll();
    Optional<Customer> findByCustomerId(int customerId);

}
