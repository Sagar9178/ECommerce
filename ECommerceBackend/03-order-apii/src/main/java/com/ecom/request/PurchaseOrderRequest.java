package com.ecom.request;

import java.util.List;

import com.ecom.dto.AddressDto;
import com.ecom.dto.CustomerDto;
import com.ecom.dto.OrderDto;
import com.ecom.dto.OrderItemsDto;

import lombok.Data;
@Data
public class PurchaseOrderRequest {
	private CustomerDto customer;
	private AddressDto address;
	private OrderDto order;
	private List<OrderItemsDto> orderItems;
}
