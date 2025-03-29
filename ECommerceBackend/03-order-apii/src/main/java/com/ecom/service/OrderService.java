package com.ecom.service;

import java.util.List;

import com.ecom.dto.OrderDto;
import com.ecom.dto.PaymentCallBackDto;
import com.ecom.request.PurchaseOrderRequest;
import com.ecom.response.PurchaseOrderResponse;

public interface OrderService {
	
	public PurchaseOrderResponse createorder(PurchaseOrderRequest orderRequest);
	
	public List<OrderDto> getOrderByEmail(String email);
	
	public PurchaseOrderResponse updateOrder(PaymentCallBackDto paymentCallBackDto);
}
