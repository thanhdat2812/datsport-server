package com.example.webshopsport.service;

import java.util.List;

import com.example.webshopsport.entities.Account;
import com.example.webshopsport.model.AccountModel;
import com.example.webshopsport.model.AccountStatusUpdateModel;
import com.example.webshopsport.model.ChangePassword;

public interface IAccountService {
	
	public int getTotalCustomer();
	public Account findByUsername(String username);
	public AccountModel findAccountByUsername(String username);
	public AccountModel create(AccountModel accountModel);
	public List<AccountModel> findAll();
	public int sendEmail(String toEmail);
	public AccountModel changePassword(ChangePassword changePass);
	public AccountModel updateInforAccount(AccountModel accountModel);
	public AccountModel updateStatusAccount(AccountStatusUpdateModel accountModel);
}
