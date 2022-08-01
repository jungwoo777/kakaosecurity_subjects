package com.example.kakaosecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.TotalBalForYear;
import com.example.kakaosecurity.response.ApiErrorEnum;
import com.example.kakaosecurity.response.ApiErrorException;
import com.example.kakaosecurity.service.CustomerServices;
import com.example.kakaosecurity.service.PostService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "gets-추가조회", description = "기타 추가 조회 API")
@RequestMapping("/gets")
public class PostController {
	
	private final PostService postService;
	private final CustomerServices customerService;
	
	@Autowired
	public PostController(PostService postService, CustomerServices customerService) {
		this.postService = postService;
		this.customerService = customerService;
	}

	@GetMapping("/getBalanceByAccountWithCustomer/customerId/{customerId}")
    public ResponseEntity<?> findBalanceByAccountWithCustomer(@PathVariable("customerId") int customerId) {
		Map<String, Object> response = new HashMap<>();
		if(customerService.findCustomerById(customerId).isEmpty()) {
			throw new ApiErrorException(ApiErrorEnum.INSERT_ERROR_CUSTOMER);
		}
		
        List<BalanceByAccountWithCustomer> list = postService.findBalanceByAccountWithCustomer(customerId);
        
        response.put("message", "Success");
        response.put("BalanceByAccountWithCustomer", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/getAvgBalByAges")
	public ResponseEntity<?> findAvgBalByAges() {
		List<AvgBalByAges> list = postService.findAvgBalByAges();
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", "Success");
		response.put("AvgBalByAges", list);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getTotalBalForYear/year/{year}")
	public ResponseEntity<?> findTotalBalForYear(@PathVariable("year") int year) {
		Map<String, Object> response = new HashMap<>();
		
		List<TotalBalForYear> list = postService.findTotalBalForYear(year);
		
		
		if(list.isEmpty()) { 
			throw new ApiErrorException(ApiErrorEnum.NO_ELEMENT);
		}
		response.put("message", "Success");
		response.put("TotalBalForYear", list);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
