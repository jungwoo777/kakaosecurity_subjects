package com.example.kakaosecurity.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.TotalBalForYear;
import com.example.kakaosecurity.domain.TotalBalViaPeriodByCustomer;
import com.example.kakaosecurity.response.ApiErrorEnum;
import com.example.kakaosecurity.response.ApiErrorException;
import com.example.kakaosecurity.service.CustomerServices;
import com.example.kakaosecurity.service.GetsService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "gets-추가조회", description = "기타 추가 조회 API")
@RequestMapping("/gets")
public class GetsController {
	
	private final GetsService getsService;
	private final CustomerServices customerService;
	
	@Autowired
	public GetsController(GetsService getsService, CustomerServices customerService) {
		this.getsService = getsService;
		this.customerService = customerService;
	}

	@GetMapping("/getBalanceByAccountWithCustomer/customerId/{customerId}")
    public ResponseEntity<?> findBalanceByAccountWithCustomer(@PathVariable("customerId - 고객ID") int customerId) {
		Map<String, Object> response = new HashMap<>();
		if(customerService.findCustomerById(customerId).isEmpty()) {
			throw new ApiErrorException(ApiErrorEnum.INSERT_ERROR_CUSTOMER);
		}
		
        List<BalanceByAccountWithCustomer> list = getsService.findBalanceByAccountWithCustomer(customerId);
        
        response.put("message", "Success");
        response.put("BalanceByAccountWithCustomer", list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/getAvgBalByAges")
	public ResponseEntity<?> findAvgBalByAges() {
		List<AvgBalByAges> list = getsService.findAvgBalByAges();
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", "Success");
		response.put("AvgBalByAges", list);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getTotalBalForYear/year/{year}")
	public ResponseEntity<?> findTotalBalForYear(@PathVariable("year - 조회년도") int year) {
		Map<String, Object> response = new HashMap<>();
		
		List<TotalBalForYear> list = getsService.findTotalBalForYear(year);
		
		
		if(list.isEmpty()) { 
			throw new ApiErrorException(ApiErrorEnum.NO_ELEMENT);
		}
		response.put("message", "Success");
		response.put("TotalBalForYear", list);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("/getTotalBalForCustomerBetweenDate/startDate/{startDate}/endDate/{endDate}")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	public ResponseEntity<?> findTotalBalForYear(@PathVariable("startDate - 조회시작일자") String startDate, @PathVariable("endDate - 조회 종료일자") String endDate) {
		Map<String, Object> response = new HashMap<>();
		
		if(!checkDate(startDate) || !checkDate(endDate)) {
			throw new ApiErrorException(ApiErrorEnum.BAD_INPUT_FORMAT_DATE);
		}
		
		List<TotalBalViaPeriodByCustomer> list = getsService.findTotalBalForCustomerViaPeriod(startDate, endDate);
		
		
		if(list.isEmpty()) { 
			throw new ApiErrorException(ApiErrorEnum.NO_ELEMENT);
		}
		response.put("message", "Success");
		response.put("TotalBalViaPeriodByCustomer", list);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private boolean checkDate(String checkDate) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd"); //검증할 날짜 포맷 설정
            dateFormatParser.setLenient(false); //false일경우 처리시 입력한 값이 잘못된 형식일 시 오류가 발생
            dateFormatParser.parse(checkDate); //대상 값 포맷에 적용되는지 확인
            return true;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
	
}
