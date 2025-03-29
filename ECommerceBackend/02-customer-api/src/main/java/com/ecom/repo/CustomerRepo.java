package com.ecom.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ecom.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	


 public Customer findByEmail(String email);


}
