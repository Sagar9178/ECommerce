package com.ecom.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.AddressDto;
import com.ecom.dto.CustomerDto;
import com.ecom.dto.OrderDto;
import com.ecom.dto.OrderItemsDto;
import com.ecom.dto.PaymentCallBackDto;
import com.ecom.entity.Address;
import com.ecom.entity.Customer;
import com.ecom.entity.Order;
import com.ecom.entity.OrderItems;
import com.ecom.repo.Addressrepo;
import com.ecom.repo.CustomerRepo;
import com.ecom.repo.OrderItemRepo;
import com.ecom.repo.OrderRepo;
import com.ecom.request.PurchaseOrderRequest;
import com.ecom.response.PurchaseOrderResponse;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private OrderItemRepo itemRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private Addressrepo addressrepo;
	@Autowired
	private RazorPayService payService;
	@Autowired
	private EmailService emailService;

	@Override
	public PurchaseOrderResponse createorder(PurchaseOrderRequest orderRequest) {
		CustomerDto customerDto = orderRequest.getCustomer();
		AddressDto addressDto = orderRequest.getAddress();
		OrderDto orderDto = orderRequest.getOrder();
		List<OrderItemsDto> orderItemsList = orderRequest.getOrderItems();
		
		
		
		Customer c = customerRepo.findByEmail(customerDto.getEmail());
		
		if(c==null) {
			c=new Customer();
			c.setEmail(customerDto.getEmail());
			c.setName(customerDto.getName());
			c.setPhno(customerDto.getPhno());
			
			customerRepo.save(c);
		}
		
		
		Address address=new Address();
		address.setCity(addressDto.getCity());
		address.setHouseNum(addressDto.getHouseNum());
		address.setStreet(addressDto.getStreet());
		address.setZipCode(addressDto.getZipCode());
		address.setState(addressDto.getState());
		address.setCustomer(c);
		addressrepo.save(address);
		
		Order newOrder=new Order();
		String generateorderTrakingNum = generateorderTrakingNum();
		newOrder.setOrderTrackingNum(generateorderTrakingNum);

		com.razorpay.Order paymentOrder = payService.createPaymentOrder(orderDto.getTotalPrice());
		newOrder.setRazorPayOrderId(paymentOrder.get("id"));
		newOrder.setOrderStatus(paymentOrder.get("status"));
		newOrder.setAddress(address);
		newOrder.setCustomer(c);
		newOrder.setEmail(c.getEmail());
		newOrder.setTotalPrice(orderDto.getTotalPrice());
		newOrder.setTotalQuantity(orderDto.getTotalQuantity());
		
		orderRepo.save(newOrder);
		
		for(OrderItemsDto item:orderItemsList) {
			OrderItems i=new OrderItems();
			BeanUtils.copyProperties(item, i);
			i.setId(null);
			i.setOrder(newOrder);
			itemRepo.save(i);
		}
		
		//response
		
		PurchaseOrderResponse response=PurchaseOrderResponse.builder()
				.razorPayOrderId(paymentOrder.get("id"))
				.orderstatus(paymentOrder.get("status"))
				.orderTrackingNumber(generateorderTrakingNum)
				.build();
		
		return response;
	}

	@Override
	public List<OrderDto> getOrderByEmail(String email) {
		List<Order> orderList = orderRepo.findByEmail(email);
		List<OrderDto> dtoList=new ArrayList<>();
		
		for(Order o:orderList) {
			OrderDto od=new OrderDto();
			BeanUtils.copyProperties(o, od);
			dtoList.add(od);
		}
		return dtoList;
	}

	@Override
	public PurchaseOrderResponse updateOrder(PaymentCallBackDto paymentCallBackDto) {
		Order order = orderRepo.findByRazorPayOrderId(paymentCallBackDto.getRazorpayOrderId());
		if(order!=null) {
			order.setOrderStatus("CONFIRMED");
			order.setRazorPayOrderId(paymentCallBackDto.getRazorpayOrderId());
			order.setDeliveryDate(LocalDate.now().plusDays(3));
			orderRepo.save(order);
			
			String Sub="Your Order Confirmed.";
			String body="Thankyou, you will recieve your order on "+order.getDeliveryDate();
			emailService.sendEmail(order.getEmail(), Sub, body);
			
		}
		
		PurchaseOrderResponse response=PurchaseOrderResponse.builder()
				.razorPayOrderId(paymentCallBackDto.getRazorpayOrderId())
				.orderstatus(order.getOrderStatus())
				.orderTrackingNumber(order.getOrderTrackingNum())
				.build();
		return response;
	}
	
	private String generateorderTrakingNum() {
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStamp = sdf.format(new Date());
		
		String Uuid = UUID.randomUUID().toString().substring(0,5).toUpperCase();
		
		return "OD"+timeStamp+Uuid;
	}

}
