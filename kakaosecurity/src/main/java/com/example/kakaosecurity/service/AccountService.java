package com.example.kakaosecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kakaosecurity.domain.Account;
import com.example.kakaosecurity.repository.AccountRepository;


/**
 * 계좌 service
 * @author jungwoo
 *
 */
@Service
public class AccountService {
	private final AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Long createNewAccount(Account account) {
		Account newAccount = accountRepository.save(account);
		return newAccount.getId();
	}
	
	public List<Account> findAllAccounts(){
		return accountRepository.findAll();
	}
	
	public Optional<Account> findAccountByAccountNo(String accountNo){
		return accountRepository.findByAccountNo(accountNo);
	}

}
