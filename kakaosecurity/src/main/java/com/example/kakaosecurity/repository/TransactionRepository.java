package com.example.kakaosecurity.repository;

import java.util.List;

import com.example.kakaosecurity.domain.Transaction;

public interface TransactionRepository {
	
	Transaction save(Transaction transaction);
    List<Transaction> findAll();

}
