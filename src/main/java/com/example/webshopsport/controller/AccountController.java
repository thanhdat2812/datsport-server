package com.example.webshopsport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webshopsport.config.JwtUtil;
import com.example.webshopsport.entities.Account;
import com.example.webshopsport.model.AccountDetailRequest;
import com.example.webshopsport.model.AccountModel;
import com.example.webshopsport.model.AccountStatusUpdateModel;
import com.example.webshopsport.model.AuthenticationRequest;
import com.example.webshopsport.model.AuthenticationResponse;
import com.example.webshopsport.model.ChangePassword;
import com.example.webshopsport.model.ResponseObject;
import com.example.webshopsport.model.SendMail;
import com.example.webshopsport.service.IAccountService;
import com.example.webshopsport.service.MyUserDetailsService;

@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	IAccountService accountService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtTokenUtil;
	
	@GetMapping("/admin/gettotalcustomer")
	public ResponseEntity<ResponseObject> getTotalCustomer() {
		int result = accountService.getTotalCustomer();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Get Total Customer Success", result));
	}

	@GetMapping("/admin/listaccount")
	public ResponseEntity<ResponseObject> getListAccount() {
		List<AccountModel> listAccount = accountService.findAll();
		if(listAccount != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Get List Account Success", listAccount));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Don't Account To Get", null));
	}
	
	@PostMapping("/api/findaccountbyusername")
	public ResponseEntity<ResponseObject> getUserDetailByUsername(@RequestBody AccountDetailRequest accountDetailRequest){
		
		AccountModel accModel=accountService.findAccountByUsername(accountDetailRequest.getUsername());
		if(accModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Get Account Success", accModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Get Account Failed", null));
		
	}
	
	@PostMapping("/admin/updatestatusaccount")
	public ResponseEntity<ResponseObject> updateStatusAccount(@RequestBody AccountStatusUpdateModel accountModel) {
		System.out.println("Account:"+accountModel);
		AccountModel accModel = accountService.updateStatusAccount(accountModel);
		if(accModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Status Account Success", accModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Status Account Failed", null));
	}

	@PostMapping("/api/authenticate")
	public ResponseEntity<ResponseObject> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		Account account = accountService.findByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject(200, "Login Success", new AuthenticationResponse(token, account.getUserId(),account.getUserImage(), account.getUserName(), account.getUserEmail(), account.getUserRole())));

//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//				new ResponseObject("Failded", "Username or Password Incorrect", null));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	
	@PostMapping("/api/register")
	public ResponseEntity<ResponseObject> create(@RequestBody AccountModel accountModel) {
		AccountModel accModel = accountService.create(accountModel);
		return (accModel != null)
				? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Create Success", null))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(400, "Cannot create account, Account is exists", null));
	}
	
	@PostMapping("/api/forgotpassword")
	public ResponseEntity<ResponseObject> forgotPassword(@RequestBody SendMail sendMail) {
		int result = accountService.sendEmail(sendMail.getToMail());
		if(result == 1) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Send Mail Success", null));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Send Mail Failed", null));
	}
	
	@PostMapping("/api/changepassword")
	public ResponseEntity<ResponseObject> changePassword(@RequestBody ChangePassword changePassModel) {
		System.out.println(changePassModel);
		AccountModel accountModel = accountService.changePassword(changePassModel);
		if(accountModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Change Password Success", accountModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Change Password Failed", null));
	}
	
	@PostMapping("/api/updateinforaccount")
	public ResponseEntity<ResponseObject> updateInforAccount(@RequestBody AccountModel accountModel) {
		AccountModel accModel = accountService.updateInforAccount(accountModel);
		if(accModel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Infor Success", accModel));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200, "Update Infor Failed", null));
	}
}
