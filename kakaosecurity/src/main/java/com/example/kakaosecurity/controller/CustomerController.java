package com.example.kakaosecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaosecurity.domain.Customer;
import com.example.kakaosecurity.form.CustomerForm;
import com.example.kakaosecurity.response.ApiErrorEnum;
import com.example.kakaosecurity.response.ApiErrorException;
import com.example.kakaosecurity.service.AccountService;
import com.example.kakaosecurity.service.CustomerServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "customer-고객", description = "고객 관련 API")
public class CustomerController {
	
	private final CustomerServices customerServices;
	
	public CustomerController(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@PostMapping("/customer/new")
	public ResponseEntity<?> createNewCustomer(CustomerForm customerForm){
		Map<String, Object> response = new HashMap<>();
		
		if(customerServices.findCustomerById(customerForm.getCustomerId()).isPresent()) {
			throw new ApiErrorException(ApiErrorEnum.DUPLICATED_CUSTOMER);
		}
		
		Customer customer = new Customer();
        customer.setCustomerId(customerForm.getCustomerId());
        customer.setName(customerForm.getName());
        customer.setAge(customerForm.getAge());
        customer.setEnterDate(customerForm.getEnterDate());

        customerServices.createNewCustomer(customer);
        
        response.put("message", "success");
		response.put("customer", customer);
        
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/customer/all")
    public ResponseEntity<?> readAll() {
        List<Customer> customers = customerServices.findAllCustomers();

        // Response에 넣어준다
        Map<String, Object> response = new HashMap<>();
        response.put("customers", customers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> readAll(@PathVariable("customerId-고객ID") int customerId) {
    	Map<String, Object> response = new HashMap<>();
    	
        Optional<Customer> customer = customerServices.findCustomerById(customerId);


        if(!customer.isPresent()) {
        	throw new ApiErrorException(ApiErrorEnum.INSERT_ERROR_CUSTOMER);
        } 

        response.put("message", "Success");
        response.put("member", customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	

}
