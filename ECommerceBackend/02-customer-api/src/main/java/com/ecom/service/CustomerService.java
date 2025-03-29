package com.ecom.service;

import com.ecom.dto.CustomerDto;

import com.ecom.dto.ResetPwdDto;
import com.ecom.response.AuthResponse;

public interface CustomerService {
	
	public Boolean register(CustomerDto customerDto);
	
	public Boolean isEmailUnique(String email);
	
	public Boolean resetpwd(ResetPwdDto resetPwdDto);
	
	public AuthResponse login(CustomerDto customerDto);
	
	public CustomerDto getCustomerByEmail(String email);

	public Boolean forgetpwd(String email);
}
