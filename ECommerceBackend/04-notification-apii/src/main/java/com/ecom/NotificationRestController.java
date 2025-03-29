package com.ecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.service.NotificationService;

@RestController
public class NotificationRestController {
	@Autowired
	private NotificationService notificationService;
	@GetMapping("/demo")
	public String demo() {
		notificationService.sendDeliveryNotification();
		return "success";
	}
}
