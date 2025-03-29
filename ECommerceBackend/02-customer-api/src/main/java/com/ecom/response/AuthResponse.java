package com.ecom.response;

import com.ecom.dto.CustomerDto;

import lombok.Data;
@Data
public class AuthResponse {
	
	private CustomerDto customer;
	
	private String token;
}
