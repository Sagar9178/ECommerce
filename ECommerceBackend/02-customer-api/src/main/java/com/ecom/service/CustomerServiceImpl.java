package com.ecom.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecom.dto.CustomerDto;

import com.ecom.dto.ResetPwdDto;
import com.ecom.entity.Customer;
import com.ecom.mapper.CustomerMapper;
import com.ecom.repo.CustomerRepo;

import com.ecom.response.AuthResponse;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AuthenticationManager authManager;
	Random rnd = new Random();

	

	@Override
	public Boolean register(CustomerDto customerDto) {
		String randomPwd = getRandomPwd();
		
			
			Customer c = CustomerMapper.convertToentity(customerDto);
			c.setPwd(bcrypt.encode(randomPwd));
			c.setPwdUpdated("NO");
		
			Customer save = customerRepo.save(c);
			if(save.getId()!=null) {
				String subject="Registation Succesful....";
				String body=" your temporary password is "+randomPwd;
				return emailService.sendEmail(save.getEmail(), subject, body);
				
			}else {
				return false;
			}
			
		
	
		
	}

	@Override
	public Boolean isEmailUnique(String email) {
		Customer byEmail = customerRepo.findByEmail(email);
		
		return byEmail==null;
		
		
	}

	@Override
	public Boolean resetpwd(ResetPwdDto resetPwdDto) {
		
			 Customer c = customerRepo.findByEmail(resetPwdDto.getEmail());
			 c.setPwdUpdated("YES");
			
			 c.setPwd(bcrypt.encode(resetPwdDto.getNewPwd()) );
			 customerRepo.save(c);
			 return true;
		
		
		
	}

	@Override
	public AuthResponse login(CustomerDto customerDto) {
		
		AuthResponse response=null;
		
		UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(customerDto.getEmail(), customerDto.getPwd());
		
		Authentication authenticate = authManager.authenticate(authToken);
		if(authenticate.isAuthenticated()) {
			response=new AuthResponse();
			Customer c = customerRepo.findByEmail(customerDto.getEmail());
			response.setCustomer(CustomerMapper.convertToDto(c));
			response.setToken("");
		}
		
		
		return response;
	}

	@Override
	public CustomerDto getCustomerByEmail(String email) {
		Customer c = customerRepo.findByEmail(email);
		if(c!=null) {
			return CustomerMapper.convertToDto(c);

		}else {
			return null;
		}
	}
	
	private String getRandomPwd() {

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

		StringBuilder pwd = new StringBuilder();

		

		while (pwd.length() < 5) { 
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			pwd.append(SALTCHARS.charAt(index));
		}

		return pwd.toString();
	}

	@Override
	public Boolean forgetpwd(String email) {
		Customer c = customerRepo.findByEmail(email);
		if(c!=null) {
			String subject="Reset pwd Request";
			String body="temp body";
			emailService.sendEmail(email, subject, body);
			return true;
		}
		return false;
	}


}
