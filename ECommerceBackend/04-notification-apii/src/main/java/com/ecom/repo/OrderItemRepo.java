package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.OrderItems;

public interface OrderItemRepo extends JpaRepository<OrderItems, Long>{

}
