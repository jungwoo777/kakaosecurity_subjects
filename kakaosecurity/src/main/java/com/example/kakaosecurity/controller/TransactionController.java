package com.example.kakaosecurity.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakaosecurity.domain.Transaction;
import com.example.kakaosecurity.form.TransactionForm;
import com.example.kakaosecurity.service.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * 거래정보 컨트롤러
 * @author jungwoo
 *
 */
@RestController
@Tag(name = "transaction-거래", description = "거래 관련 API")
public class TransactionController {
	private final TransactionService transactionService;
	
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
	
	
	/**
	 * 거래 신규
	 * @param transactionForm
	 * @return
	 */
	@PostMapping("/transaction/new")
	public ResponseEntity<?> createNewCustomer(TransactionForm transactionForm){
		Map<String, Object> response = new HashMap<>();
		
		Transaction transaction = new Transaction();
		transaction.setAccountNo(transactionForm.getAccountNo());
		transaction.setTransferYn(transactionForm.getTransferYn());
		transaction.setAmmount(transactionForm.getAmmount());
		transaction.setTransferDate(transactionForm.getTransferDate());

		transactionService.createNewTransaction(transaction);
		
		response.put("message", "success");
		response.put("transaction", transaction);
		
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	/**
	 * 모든 거래 조회
	 * @return
	 */
	@GetMapping("/transaction/all")
    public ResponseEntity<?> readAll() {
		Map<String, Object> response = new HashMap<>();
		
        List<Transaction> transactions = transactionService.findAllTransactions();

        response.put("message", "success");
        response.put("transactions", transactions);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
