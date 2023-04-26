package com.example.webshopsport.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.webshopsport.entities.Account;

@Service 
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	IAccountService accountService;
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Account account  = accountService.findByUsername(username);
//		return new User(account.getUserName(), account.getUserPass(), new ArrayList<>());
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account  = accountService.findByUsername(username);
		if(account.getUserName().equals(username)) {
			return new User(account.getUserName(), account.getUserPass(),
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}