package com.ecom.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(nullable = true)
	private Long Orderid;
	
	private String orderTrackingNum;
	
	private Integer totalQuantity;
	
	private Long totalPrice;
	
	private String email;
	
	private String orderStatus ;
	
	private String invoiceUrl;
	
	
	 @CreationTimestamp
	 @Column(name="date_created", updatable = false)
	 private LocalDate dateCreated;
	@UpdateTimestamp
	private LocalDate lastUpdated;
	
	@CreationTimestamp
	 private LocalDate deliveryDate;
	
	private String razorPayOrderId;	
	
	private String razorPayPaymentId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "addr_id")
	private Address address;

		
}
