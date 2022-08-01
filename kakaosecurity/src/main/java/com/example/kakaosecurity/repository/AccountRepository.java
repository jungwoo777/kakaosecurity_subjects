package com.example.kakaosecurity.repository;

import java.util.List;
import java.util.Optional;

import com.example.kakaosecurity.domain.Account;

public interface AccountRepository {
	
	Account save(Account account);
    List<Account> findAll();
    Optional<Account> findByAccountNo(String accountNo);

}
