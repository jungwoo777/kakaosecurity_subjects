package com.example.kakaosecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.TotalBalForYear;
import com.example.kakaosecurity.repository.PostsRepository;

@Service
public class PostService {
	
	private final PostsRepository postsRepository;
	
	public PostService(PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
	}
	
	public List<BalanceByAccountWithCustomer> findBalanceByAccountWithCustomer(int customerId){
		return postsRepository.findBalanceByAccountWithCustomer(customerId); 
	}
    public List<AvgBalByAges> findAvgBalByAges(){
    	return postsRepository.findAvgBalByAges(); 
    }
    public List<TotalBalForYear> findTotalBalForYear(int year){
    	return postsRepository.findTotalBalForYear(year);
    }

}
