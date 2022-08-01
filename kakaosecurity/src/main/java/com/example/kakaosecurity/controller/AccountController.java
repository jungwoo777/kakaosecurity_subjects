package com.example.kakaosecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaosecurity.domain.Account;
import com.example.kakaosecurity.form.AccountForm;
import com.example.kakaosecurity.response.ApiErrorEnum;
import com.example.kakaosecurity.response.ApiErrorException;
import com.example.kakaosecurity.service.AccountService;
import com.example.kakaosecurity.service.CustomerServices;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "account-계좌", description = "계좌관련 API")
public class AccountController {
	
	private final AccountService accountService;
	private final CustomerServices customerService;
	
	public AccountController(AccountService accountService, CustomerServices customerService) {
		this.accountService = accountService;
		this.customerService = customerService; 
	}
	
	@PostMapping("/account/new")
	public ResponseEntity<?>  createNewAccount(AccountForm accountForm) {
		Map<String, Object> response = new HashMap<>();
		
		if(customerService.findCustomerById(accountForm.getCustomerId()).isEmpty()) {
			throw new ApiErrorException(ApiErrorEnum.INSERT_ERROR_CUSTOMER);
		}
		else if(accountService.findAccountByAccountNo(accountForm.getAccountNo()).isPresent()) {
			throw new ApiErrorException(ApiErrorEnum.DUPLICATED_ACCOUNT);
		}
		Account account = new Account();
		account.setAccountNo(accountForm.getAccountNo());
		account.setCustomerId(accountForm.getCustomerId());
		accountService.createNewAccount(account);
		
		response.put("message", "success");
		response.put("account", account);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/account/all")
    public ResponseEntity<?> readAll() {
		Map<String, Object> response = new HashMap<>();
		
        List<Account> accounts = accountService.findAllAccounts();
        

        // Response에 넣어준다
        response.put("message", "success");
        response.put("accounts", accounts);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/account/{accountNo}")
    public ResponseEntity<?> readAll(@PathVariable("accountNo") String accountNo) {
        Optional<Account> account = accountService.findAccountByAccountNo(accountNo);

        Map<String, Object> response = new HashMap<>();

        if(!account.isPresent()) { 
        	throw new ApiErrorException(ApiErrorEnum.INSERT_ERROR_ACCOUNT);
        }
        
        response.put("message", "Success");
        response.put("account", account);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
