package com.ecom.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.OrderDto;
import com.ecom.dto.PaymentCallBackDto;
import com.ecom.request.PurchaseOrderRequest;
import com.ecom.response.ApiResponse;
import com.ecom.response.PurchaseOrderResponse;
import com.ecom.service.OrderService;

@RestController
@CrossOrigin
public class OrderRestController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> createOrder(@RequestBody PurchaseOrderRequest request){
		PurchaseOrderResponse response = orderService.createorder(request);
		ApiResponse<PurchaseOrderResponse> apiResponse=new ApiResponse<>();
		if(response!=null) {
			apiResponse.setStatus(200);
			apiResponse.setMessage("order Created");
			apiResponse.setData(response);
			return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		}else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Failed to Create Order");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/order")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> updateOrder(@RequestBody PaymentCallBackDto request){
		PurchaseOrderResponse response = orderService.updateOrder(request);
		ApiResponse<PurchaseOrderResponse> apiResponse=new ApiResponse<>();
		if(response!=null) {
			apiResponse.setStatus(200);
			apiResponse.setMessage("Order Updated");
			apiResponse.setData(response);
			return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		}else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Failed Update");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/orders/{email}")
	public ResponseEntity<ApiResponse<List<OrderDto>>> getOrders(@PathVariable String email){
		List<OrderDto> orders = orderService.getOrderByEmail(email);
		ApiResponse<List<OrderDto>> apiResponse=new ApiResponse<>();
		if(orders!=null) {
			apiResponse.setStatus(200);
			apiResponse.setMessage(" Fetch Orders");
			apiResponse.setData(orders);
			return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		}else {
			apiResponse.setStatus(500);
			apiResponse.setMessage("Failed To Fetch");
			apiResponse.setData(null);
			return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
