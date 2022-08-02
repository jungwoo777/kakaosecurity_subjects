package com.example.kakaosecurity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.TotalBalForYear;
import com.example.kakaosecurity.domain.TotalBalViaPeriodByCustomer;
import com.example.kakaosecurity.repository.GetsRepository;

@Service
public class GetsService {
	
	private final GetsRepository getsRepository;
	
	public GetsService(GetsRepository postsRepository) {
		this.getsRepository = postsRepository;
	}
	
	public List<BalanceByAccountWithCustomer> findBalanceByAccountWithCustomer(int customerId){
		return getsRepository.findBalanceByAccountWithCustomer(customerId); 
	}
    public List<AvgBalByAges> findAvgBalByAges(){
    	return getsRepository.findAvgBalByAges(); 
    }
    public List<TotalBalForYear> findTotalBalForYear(int year){
    	return getsRepository.findTotalBalForYear(year);
    }
    public List<TotalBalViaPeriodByCustomer> findTotalBalForCustomerViaPeriod(String startDate, String endDate){
    	return getsRepository.findTotalBalViaPeriodByCustomer(startDate, endDate);
    }

}
