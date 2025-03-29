package com.ecom.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecom.entity.Customer;
import com.ecom.repo.CustomerRepo;
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepo customerrepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer c = customerrepo.findByEmail(email);
		return new User(c.getEmail(), c.getPwd(), Collections.emptyList());
	}

}
