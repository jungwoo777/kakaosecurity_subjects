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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 계좌 - 컨트롤러
 * @author jungwoo
 *
 */
@RestController
@Tag(name = "account-계좌", description = "계좌관련 API")
public class AccountController {
	
	private final AccountService accountService;
	private final CustomerServices customerService;
	
	public AccountController(AccountService accountService, CustomerServices customerService) {
		this.accountService = accountService;
		this.customerService = customerService; 
	}
	
	/**
	 * 계좌 신규
	 * @param accountForm
	 * @return
	 */
	@PostMapping("/account/new")
	@Operation(summary="계좌 신규 등록", description="계좌정보를 입력받아 신규 계좌를 등록한다")
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
	
	
	/**
	 * 전체계좌 조회
	 * @return
	 */
	@GetMapping("/account/all")
	@Operation(summary="전체 계좌 조회", description="관리하고 있는 모든 계좌를 조회한다.")
    public ResponseEntity<?> readAll() {
		Map<String, Object> response = new HashMap<>();
		
        List<Account> accounts = accountService.findAllAccounts();
        

        // Response에 넣어준다
        response.put("message", "success");
        response.put("accounts", accounts);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

	
	/**
	 * 계좌 한건 조회
	 * @param accountNo : 계좌번호
	 * @return
	 */
    @GetMapping("/account/{accountNo}")
    @Operation(summary="계좌 한건 조회", description="계좌번호를 입력받아 계좌 한건을 조회한다.")
    public ResponseEntity<?> readAll(@PathVariable("accountNo - 계좌번호") String accountNo) {
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
