package com.ecom.dto;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDto {

	private Long Orderid;
	
	private String orderTrackingNum;
	
	private Integer totalQuantity;
	
	private Long totalPrice;
	
	private String email;
	
	
	private String orderStatus ;
	
	private String invoiceUrl;
	
	private LocalDate deliveryDate;
	
	private String razorPayOrderId;	
	
	private String razorPayPaymentId;
	
		
}
