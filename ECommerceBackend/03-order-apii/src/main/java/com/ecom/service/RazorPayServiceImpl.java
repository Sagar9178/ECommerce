package com.ecom.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.SneakyThrows;
@Service
public class RazorPayServiceImpl implements RazorPayService {
	@Value("${razorpay.key}")
	private String keyId;
	@Value("${razorpay.secret}")
	private String keySecret; 
	
	private RazorpayClient client;

	@Override
	@SneakyThrows
	public Order createPaymentOrder(Long amount) {
		
		this.client=new RazorpayClient(keyId, keySecret);
		
		JSONObject orderRequest=new JSONObject();
		orderRequest.put("amount", amount*100); //amount in paisa
		orderRequest.put("currency", "INR");
		orderRequest.put("payment_capture", 1);
		
		Order order = client.orders.create(orderRequest);
		return order;
	}

}
