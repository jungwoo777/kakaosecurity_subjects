package com.example.kakaosecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kakaosecurity.domain.Transaction;
import com.example.kakaosecurity.repository.TransactionRepository;


/**
 * 거래정보 service
 * @author jungwoo
 *
 */
@Service
public class TransactionService {
	
	
	private final TransactionRepository tansactionRepository;
	
	@Autowired
	public TransactionService(TransactionRepository tansactionRepository) {
		this.tansactionRepository = tansactionRepository;
	}
	
	public Long createNewTransaction (Transaction transaction) {
		Transaction newTransaction = tansactionRepository.save(transaction);
		return newTransaction.getId();
	}
	
	public List<Transaction> findAllTransactions(){
		return tansactionRepository.findAll();
	}
	
	
	
	

}
