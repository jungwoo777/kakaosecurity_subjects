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
import com.example.kakaosecurity.service.CustomerServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 고객 컨트롤러
 * @author jungwoo
 *
 */
@RestController
@Tag(name = "customer-고객", description = "고객 관련 API")
public class CustomerController {
	
	private final CustomerServices customerServices;
	
	public CustomerController(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}
	
	
	/**
	 * 고객 신규
	 * @param customerForm
	 * @return
	 */
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@PostMapping("/customer/new")
	@Operation(summary="고객 신규 등록", description="고객정보를 입력받아 신규고객을 등록한다.")
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
	
	
	/**
	 * 전체 고객 목록 조회
	 * @return
	 */
	@GetMapping("/customer/all")
	@Operation(summary="전체 고객 조회", description="관리하고 있는 모든 고객을 조회한다.")
    public ResponseEntity<?> readAll() {
        List<Customer> customers = customerServices.findAllCustomers();

        // Response에 넣어준다
        Map<String, Object> response = new HashMap<>();
        response.put("customers", customers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	
	/**
	 * 고객 한명 조회
	 * @param customerId
	 * @return
	 */
    @GetMapping("/customer/{customerId}")
    @Operation(summary="고객 한건 조회", description="고객ID를 입력받아 고객 한명의 정보를 조회한다.")
    public ResponseEntity<?> readAll(String customerId) {
    	Map<String, Object> response = new HashMap<>();
    	
    	int customerIdValue = Integer.parseInt(customerId);
    	
        Optional<Customer> customer = customerServices.findCustomerById(customerIdValue);


        if(!customer.isPresent()) {
        	throw new ApiErrorException(ApiErrorEnum.INSERT_ERROR_CUSTOMER);
        } 

        response.put("message", "Success");
        response.put("member", customer);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	

}
