package com.paypal.notificationms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.notificationms.pojo.GenericResponse;
import com.paypal.notificationms.pojo.NotificationListResponse;
import com.paypal.notificationms.pojo.SendNotificationRequest;
import com.paypal.notificationms.service.NotificationServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class NotificationController {
	
	@Autowired
	private NotificationServiceImpl notificationServiceImpl;
	
	@PostMapping("/api/notificationms/v1/notify")
	public GenericResponse sendNotification(@RequestBody SendNotificationRequest sendNotificationRequest) {
		long currentTime = System.currentTimeMillis();
		try {
			return notificationServiceImpl.sendNotification(sendNotificationRequest);
		} finally {
			log.debug("Time taken to sendNotification: {}", (System.currentTimeMillis()- currentTime));
		}
	}
	
	
	@GetMapping("/api/notificationms/v1/notification")
	public NotificationListResponse getNotificationsByUserId(@RequestParam(name= "userId") Long userId) {
		long currentTime = System.currentTimeMillis();
		try {
			return notificationServiceImpl.getNotificationsByUserId(userId);
		} finally {
			log.debug("Time taken to getNotificationsByUserId: {}", (System.currentTimeMillis()- currentTime));
		}
	}
	
}
