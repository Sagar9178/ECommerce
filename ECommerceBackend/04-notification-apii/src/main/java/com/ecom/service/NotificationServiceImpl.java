package com.ecom.service;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom.dto.WatiParameters;
import com.ecom.dto.WatiRequest;
import com.ecom.dto.WatiResponse;
import com.ecom.entity.Customer;
import com.ecom.entity.Order;
import com.ecom.repo.OrderRepo;

@Service
public class NotificationServiceImpl implements NotificationService{
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private EmailService emailService;
//	@Value("${wati.token}")
//	private String watiToken;
//	@Value("${wati.template.name}")
//	private String templateName;
//	@Value("${wati.endpoint.url}")
//	private String watiEndPointUrl;
	
	
	
	@Override
	public Integer sendDeliveryNotification() {
		List<Order> orders = orderRepo.findByDeliveryDate(LocalDate.now());
		
		for(Order order:orders) {
			Customer customer = order.getCustomer();
			sendEmailNotification(customer.getEmail(), order.getOrderTrackingNum());
//			sendWatiNotification(customer, order.getOrderTrackingNum()); 
		}
		return orders.size();
	}

	@Override
	public Integer sendNotificationToPendingOrders() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean sendEmailNotification(String to,String orderTrackingNum) {
		String sub="Your Order Out For Delivery";
		String body="Your Order "+ orderTrackingNum+" Will Deliver Today";
		return emailService.sendEmail(to, sub, body);
	}
//	public WatiResponse sendWatiNotification(Customer customer,String orderTrackingNum) {
//		 
//		RestTemplate rt=new RestTemplate();
//		String apiUrl=watiEndPointUrl+ "?whatsappNumber=91"+customer.getPhno() ;
//		
//		WatiParameters nameParam=new WatiParameters();
//		nameParam.setName("name");
//		nameParam.setValue(customer.getName());
//		
//		WatiParameters trackingParam=new WatiParameters();
//		trackingParam.setName("order_tracking_number");
//		trackingParam.setValue(orderTrackingNum);
//		
//		WatiRequest request=new WatiRequest();
//		request.setTemplate_name(templateName);
//		request.setBroadcast_name(templateName+"BD");
//		request.setParameters(Arrays.asList(nameParam,trackingParam));
//		
//		
//		HttpHeaders headers=new HttpHeaders();
//		headers.add("Authorization", watiToken);
//		
//		HttpEntity<WatiRequest> reqEntity=new HttpEntity<WatiRequest>(request, headers);
//		ResponseEntity<WatiResponse> postForEntity = rt.postForEntity(apiUrl, request, WatiResponse.class);
//		
//		WatiResponse body = postForEntity.getBody();
//		
//		return body;
//	}

}
