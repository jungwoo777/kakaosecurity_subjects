package com.example.kakaosecurity.repository;

import java.util.List;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.TotalBalForYear;

public interface PostsRepository {
	
    List<BalanceByAccountWithCustomer> findBalanceByAccountWithCustomer(int customerId);
    List<AvgBalByAges> findAvgBalByAges();
    List<TotalBalForYear> findTotalBalForYear(int year);

}
