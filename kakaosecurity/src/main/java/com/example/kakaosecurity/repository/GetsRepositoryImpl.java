package com.example.kakaosecurity.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.kakaosecurity.domain.AvgBalByAges;
import com.example.kakaosecurity.domain.AvgBalByAgesRowMapper;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomer;
import com.example.kakaosecurity.domain.BalanceByAccountWithCustomerRowMapper;
import com.example.kakaosecurity.domain.TotalBalForYear;
import com.example.kakaosecurity.domain.TotalBalViaPeriodByCustomer;
import com.example.kakaosecurity.domain.TotalBalViaPeriodByCustomerRowMapper;
import com.example.kakaosecurity.domain.TotalBalanceForYearRowMapper;


/**
 * 추가질문 repository
 * @author jungwoo
 *
 */
@Repository
public class GetsRepositoryImpl implements GetsRepository{
	
	private final JdbcTemplate jdbcTemplate;
	private final BalanceByAccountWithCustomerRowMapper blanceByAccountWithCustomerRowMapper;
	private final AvgBalByAgesRowMapper avgBalByAgesRowMapper;
	private final TotalBalanceForYearRowMapper totalBalanceForYearRowMapper;
	private final TotalBalViaPeriodByCustomerRowMapper totalBalViaPeriodByCustomerRowMapper;
	
	@Autowired
	public GetsRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.blanceByAccountWithCustomerRowMapper = new BalanceByAccountWithCustomerRowMapper();
		this.avgBalByAgesRowMapper = new AvgBalByAgesRowMapper();
		this.totalBalanceForYearRowMapper = new TotalBalanceForYearRowMapper();
		this.totalBalViaPeriodByCustomerRowMapper = new TotalBalViaPeriodByCustomerRowMapper();
	}

	@Override
	public List<BalanceByAccountWithCustomer> findBalanceByAccountWithCustomer(int customerId) {
		List<BalanceByAccountWithCustomer> result = jdbcTemplate.query(
				"select customer_id, account_no, sum(ammount) as total_ammount\r\n"
				+ "from(select t1.customer_id as customer_id, \r\n"
				+ "            t3.account_no as account_no, \r\n"
				+ "            t3.trans_date as trans_date,\r\n"
				+ "            case when t3.transfer_yn = 'Y' then ammount\r\n"
				+ "                 else -1*t3.ammount end ammount\r\n"
				+ "      from customer t1, account t2, transaction t3\r\n"
				+ "     where t1.customer_id = t2.customer_id\r\n"
				+ "       and t2.account_no = t3.account_no) tt1\r\n"
				+ "where tt1.customer_id = ?\r\n"
				+ "group by customer_id, account_no"
				, blanceByAccountWithCustomerRowMapper
				, customerId);
		return result;
	}

	@Override
	public List<AvgBalByAges> findAvgBalByAges() {
		List<AvgBalByAges> result = jdbcTemplate.query(
				"select ages, round(AVG(ammount), 2) as avg_ammount\r\n"
				+ "from(select t1.customer_id as customer_id,\r\n"
				+ "            case when t1.age between 0 and 9 then 0\r\n"
				+ "                   when t1.age between 10 and 19 then 10\r\n"
				+ "                   when t1.age between 20 and 29 then 20\r\n"
				+ "                   when t1.age between 30 and 39 then 30\r\n"
				+ "                   when t1.age between 40 and 49 then 40\r\n"
				+ "                   when t1.age between 50 and 59 then 50\r\n"
				+ "                   when t1.age between 60 and 69 then 60\r\n"
				+ "                   when t1.age between 70 and 79 then 70\r\n"
				+ "                   when t1.age between 80 and 89 then 80\r\n"
				+ "                   when t1.age between 90 and 99 then 90\r\n"
				+ "                   else 100 end ages,\r\n"
				+ "            t3.account_no as account_no, \r\n"
				+ "            t3.trans_date as trans_date,\r\n"
				+ "            case when t3.transfer_yn = 'Y' then ammount\r\n"
				+ "                 else -1*t3.ammount end ammount\r\n"
				+ "      from customer t1, account t2, transaction t3\r\n"
				+ "     where t1.customer_id = t2.customer_id\r\n"
				+ "       and t2.account_no = t3.account_no) tt1\r\n"
				+ "group by ages"
						, avgBalByAgesRowMapper);
		return result;
	}

	@Override
	public List<TotalBalForYear> findTotalBalForYear(int year) {
		List<TotalBalForYear> result = jdbcTemplate.query(
				"select sum(ammount) as total_balance\r\n"
				+ "from(select t1.customer_id as customer_id,\r\n"
				+ "            t3.account_no as account_no, \r\n"
				+ "            t3.trans_date as trans_date,\r\n"
				+ "             substr(t3.trans_date, 0,4) as YYYY,\r\n"
				+ "            case when t3.transfer_yn = 'Y' then ammount\r\n"
				+ "                 else -1*t3.ammount end ammount\r\n"
				+ "      from customer t1, account t2, transaction t3\r\n"
				+ "     where t1.customer_id = t2.customer_id\r\n"
				+ "       and t2.account_no = t3.account_no\r\n"
				+ ") tt1\r\n"
				+ "where tt1.YYYY = ?"
				, totalBalanceForYearRowMapper
				, year);
		return result;
	}

	@Override
	public List<TotalBalViaPeriodByCustomer> findTotalBalViaPeriodByCustomer(String startDate, String endDate) {
		List<TotalBalViaPeriodByCustomer> result = jdbcTemplate.query(
				"select\r\n"
				+ "customer_id, max(name) as name, sum(ammount) as total_balance\r\n"
				+ "from(select t1.customer_id as customer_id,\r\n"
				+ "            t1.name as name,\r\n"
				+ "            t3.trans_date as trans_date,\r\n"
				+ "            case when t3.transfer_yn = 'Y' then ammount\r\n"
				+ "                 else -1*t3.ammount end ammount\r\n"
				+ "      from customer t1, account t2, transaction t3\r\n"
				+ "     where t1.customer_id = t2.customer_id\r\n"
				+ "       and t2.account_no = t3.account_no\r\n"
				+ ") tt1\r\n"
				+ "where TO_CHAR(tt1.trans_Date, 'YYYYMMDD') between ? and ?\r\n"
				+ "group by customer_id\r\n"
				+ "order by total_balance desc"
				, totalBalViaPeriodByCustomerRowMapper
				, startDate
				, endDate);
		return result;
	}

}
