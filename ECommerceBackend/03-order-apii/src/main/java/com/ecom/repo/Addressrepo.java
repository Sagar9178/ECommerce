package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Address;

public interface Addressrepo extends JpaRepository<Address, Long>{

}
