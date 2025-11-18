package com.paypal.notificationms.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.notificationms.dao.NotificationRepo;
import com.paypal.notificationms.entity.Notification;
import com.paypal.notificationms.error.ErrorDetail;
import com.paypal.notificationms.pojo.GenericResponse;
import com.paypal.notificationms.pojo.NotificationAPI;
import com.paypal.notificationms.pojo.NotificationListResponse;
import com.paypal.notificationms.pojo.SendNotificationRequest;
import com.paypal.notificationms.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationServiceImpl {

	@Autowired
	private NotificationRepo notificationRepo;

	public GenericResponse sendNotification(SendNotificationRequest sendNotificationRequest) {
		try {
			log.debug("sendNotification:: sendNotificationRequest: {}", sendNotificationRequest);

			Notification notification = Notification.builder().id(sendNotificationRequest.getId())
					.userId(sendNotificationRequest.getUserId()).message(sendNotificationRequest.getMessage())
					.createdDate(LocalDateTime.now()).build();

			notificationRepo.save(notification);

			return GenericResponse.builder().success(true).build();
		} catch (Exception e) {
			log.error(StringUtils.ERROR_STR, e.getClass(), e.getLocalizedMessage(), e);
			return GenericResponse.builder().success(false)
					.errorDetailList(Arrays.asList(ErrorDetail.INTERNAL_SERVER_ERROR)).build();
		}
	}

	public NotificationListResponse getNotificationsByUserId(String userId) {
		try {
			log.debug("getNotificationsByUserId:: userId: {}", userId);

			List<Notification> notificationList = notificationRepo.findByUserId(userId);

			List<NotificationAPI> notificationAPIList = notificationList.stream().map(notification -> {
				return NotificationAPI.builder().id(notification.getId()).userId(notification.getUserId())
						.message(notification.getMessage()).createdDate(notification.getCreatedDate()).build();
			}).toList();

			return NotificationListResponse.builder().notificationAPIList(notificationAPIList).build();

		} catch (Exception e) {
			log.error(StringUtils.ERROR_STR, e.getClass(), e.getLocalizedMessage(), e);
			return NotificationListResponse.builder().errorDetailList(Arrays.asList(ErrorDetail.INTERNAL_SERVER_ERROR))
					.build();
		}
	}

}
