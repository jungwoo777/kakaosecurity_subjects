package com.example.kakaosecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kakaosecurity.domain.Customer;
import com.example.kakaosecurity.repository.CustomerRepository;

@Service
public class CustomerServices {
	
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServices(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Long createNewCustomer(Customer customer) {
		Customer newCustomer = customerRepository.save(customer);
		return newCustomer.getId();
	}
	
	public List<Customer> findAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Optional<Customer> findCustomerById(int customerId){
		return customerRepository.findByCustomerId(customerId);
	}
	

}
