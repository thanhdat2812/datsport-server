package com.example.webshopsport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
	
	@Query("SELECT COUNT(b.billId) FROM Bill b WHERE b.billStatus = 2")
	public int getTotalOrder();
	
	@Query("SELECT SUM(b.billTotal) FROM Bill b WHERE b.billStatus = 2")
	public int getTotalMoney();
	
//	@Query("SELECT b FROM Bill b WHERE b.billStatus = 1 OR b.billStatus = -1 OR b.billStatus = 2")
//	public List<Bill> getBill();
	@Query("SELECT b FROM Bill b WHERE b.billStatus != 0")
	public List<Bill> getBill();
	
	@Query("SELECT b FROM Bill b WHERE b.account.userId = ?1 AND b.billStatus = ?2")
	public List<Bill> getBillByAccountIdAndStatusId(int accountId, int status);
	
	@Query("SELECT b FROM Bill b WHERE b.account.userId = ?1 AND b.billStatus = ?2")
	public Bill checkBillByAccountIdAndStatusId(int accountId, int status);
	
	@Query("SELECT b FROM Bill b WHERE b.account.userId = ?1 AND b.billStatus = 0")
	public Bill checkBillByAccountId(int accountId);
	
	@Query("SELECT b FROM Bill b WHERE b.account.userId = ?1 AND b.billStatus = ?2")
	public List<Bill> getAllBillByAccountIdAndStatus(int accountId,int status);
	
	@Query("SELECT b FROM Bill b WHERE b.account.userId = ?1 AND b.billStatus != 0")
	public List<Bill> getAllBillByAccountId(int accountId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Bill SET billStatus = ?1 WHERE billId = ?2")
	public int checkoutBill(int status, int billId);
	
	// @Query("SELECT SUM(b.billTotal) FROM Bill b WHERE YEAR(b.billDate) = ?1 AND b.billStatus = 2 GROUP BY MONTH(b.billDate)")
	// public List<Object> StatisticalByYear(int year);
	@Query("SELECT NEW map(MONTH(b.billDate) as month,COUNT(b) as orders, COALESCE(SUM(b.billTotal), 0) as total) FROM Bill b WHERE YEAR(b.billDate) = ?1 AND b.billStatus = 2 GROUP BY month")
	public List<Object> StatisticalByYear(int year);
}
