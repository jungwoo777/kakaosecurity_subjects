package com.example.kakaosecurity.repository;

import java.util.List;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.TotalBalForYear;
import com.example.kakaosecurity.domain.TotalBalViaPeriodByCustomer;

public interface GetsRepository {
	
    List<BalanceByAccountWithCustomer> findBalanceByAccountWithCustomer(int customerId);
    List<AvgBalByAges> findAvgBalByAges();
    List<TotalBalForYear> findTotalBalForYear(int year);
    List<TotalBalViaPeriodByCustomer> findTotalBalViaPeriodByCustomer(String startDate, String endDate);

}
