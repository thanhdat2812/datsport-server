package com.example.webshopsport.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.webshopsport.entities.Account;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("SELECT COUNT(a.userId) FROM Account a where a.userStatus = 1")
	public int getTotalCustomer();
	
	@Query("SELECT a FROM Account a where a.userName = ?1 and a.userStatus = 1")
	public Account findByUserName(String username);

	@Query("SELECT a FROM Account a where a.userName = ?1")
	public Account findByUserNameWithAllStatus(String username);
	
	@Query("SELECT a FROM Account a where a.userId = ?1")
	public Account findAccountById(int userId);
	
	@Query("SELECT a FROM Account a where a.userEmail = ?1")
	public Account findAccountByEmail(String email);
	
	

}
