package com.example.webshopsport.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.webshopsport.entities.Account;
import com.example.webshopsport.model.AccountModel;
import com.example.webshopsport.model.AccountStatusUpdateModel;
import com.example.webshopsport.model.ChangePassword;
import com.example.webshopsport.repository.AccountRepository;
import com.example.webshopsport.service.IAccountService;

@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUserName(username);
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public int getTotalCustomer() {
		int totalCustomer = accountRepository.getTotalCustomer();
		return totalCustomer;
	}

	@Override
	public AccountModel create(AccountModel accountModel) {
		Account account = convertToEntity(accountModel);
		System.out.println("Selected gender: "+account.getUserGender() );
//		account.setUserPass(BCrypt.hashpw(account.getUserPass(), BCrypt.gensalt(12)));
		Account checkUserNameAccount = new Account();
		Account checkUserEmailAccount = new Account();
		// tìm account theo username || email
		checkUserNameAccount = accountRepository.findByUserName(account.getUserName());
		checkUserEmailAccount = accountRepository.findAccountByEmail(account.getUserEmail());
		if(checkUserNameAccount != null||checkUserEmailAccount!=null) {
			return null;
		}
		else {
			accountRepository.save(account);
			return convertToDTO(account);
		}
		// tạo tài khoản, trả về null nếu không tạo được
//		return (account2 != null) ? null : accountRepository.save(account);

	}

	@Override
	public List<AccountModel> findAll() {
		ArrayList<Account> listAccount = (ArrayList<Account>) accountRepository.findAll();
		ArrayList<AccountModel> listAccountModel = new ArrayList<>();
		for (Account acc : listAccount) {
			listAccountModel.add(convertToDTO(acc));
		}
		return listAccountModel;
	}
	
	@Override
	@Transactional
	public AccountModel updateStatusAccount(AccountStatusUpdateModel accountModel) {
		Account account = accountRepository.findByUserNameWithAllStatus(accountModel.getUsername());
		if(account != null) {
			account.setUserStatus(accountModel.getStatus());
			accountRepository.save(account);
			return convertToDTO(accountRepository.findByUserNameWithAllStatus(account.getUserName()));
		}
		return null;
	}
	
	@Override
	public AccountModel updateInforAccount(AccountModel accountModel) {
		
		Account account = accountRepository.findAccountById(accountModel.getId());
		if(account != null) {
			Account acc = accountRepository.findAccountById(account.getUserId());
			acc.setUserPhone(accountModel.getPhone());
			acc.setUserFullname(accountModel.getUserfullname());
			acc.setUserAddress(accountModel.getAddress());
			acc.setUserEmail(accountModel.getEmail());
			acc.setUserImage(accountModel.getImage());
			acc.setUserGender(accountModel.getGender());
			accountRepository.save(acc);
			Account getAcc = accountRepository.findAccountById(account.getUserId());
			return convertToDTO(getAcc);
		}
		return null;
	}
	
	@Override
	public AccountModel changePassword(ChangePassword changePass) {
		Account account = accountRepository.findByUserName(changePass.getUsername());
		
		if(account != null && passwordEncoder.matches(changePass.getPassword(), account.getUserPass())) {
			Account acc = accountRepository.findAccountById(account.getUserId());
			acc.setUserId(acc.getUserId());
			acc.setUserPass(passwordEncoder.encode(changePass.getNewPassword()));
			accountRepository.save(acc);
			return convertToDTO(accountRepository.findAccountById(acc.getUserId()));
		}
		return null;
	}
	
	@Override
	public int sendEmail(String toEmail) {
		int numberOfCharactor = 8;
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		Account account = accountRepository.findAccountByEmail(toEmail);
		if(account == null) {
			return 0;
		}
		String newPassword = randomAlphaNumeric(numberOfCharactor);
		Account acc = accountRepository.findAccountById(account.getUserId());
		acc.setUserPass(passwordEncoder.encode(newPassword));
		accountRepository.save(acc);
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject("New your password from WebShopSport");
		simpleMailMessage.setText("New Your password: " + newPassword);
		this.mailSender.send(simpleMailMessage);
		return 1;
		
	}
	
	@Override
	public AccountModel findAccountByUsername(String username) {
		
		Account account = accountRepository.findByUserNameWithAllStatus(username);
		if(account==null) return null;
		return convertToDTO(account);
	}
	
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
 
    private static Random generator = new Random();
	
	public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
	
	public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
	
	public Account convertToEntity(AccountModel accountModel) {
		Account account = new Account();
		account.setUserName(accountModel.getUsername());
		account.setUserPass(BCrypt.hashpw(accountModel.getPassword(), BCrypt.gensalt(12)));
		account.setUserEmail(accountModel.getEmail());
		account.setUserFullname(accountModel.getUserfullname());
		account.setUserGender(accountModel.getGender());
		account.setUserPhone(accountModel.getPhone());
		account.setUserAddress(accountModel.getAddress());
		account.setUserImage(accountModel.getImage());
		account.setUserRole(1);
		account.setUserStatus(1);
		return account;
	}
	
	public AccountModel convertToDTO(Account account) {
		AccountModel accountModel = new AccountModel();
		accountModel.setId(account.getUserId());
		accountModel.setUsername(account.getUserName());
		accountModel.setUserfullname(account.getUserFullname());
		accountModel.setPassword(account.getUserPass());
		accountModel.setEmail(account.getUserEmail());
		accountModel.setGender(account.getUserGender());
		accountModel.setPhone(account.getUserPhone());
		accountModel.setAddress(account.getUserAddress());
		accountModel.setImage(account.getUserImage());
		accountModel.setRole(account.getUserRole());
		accountModel.setStatus(account.getUserStatus());
		return accountModel;
	}	

}
