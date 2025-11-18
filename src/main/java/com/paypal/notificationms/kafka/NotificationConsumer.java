package com.paypal.notificationms.kafka;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paypal.notificationms.dao.NotificationRepo;
import com.paypal.notificationms.entity.Notification;
import com.paypal.notificationms.pojo.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationConsumer {

	private final NotificationRepo notificationRepo;

	private final ObjectMapper objectMapper;

	public NotificationConsumer(NotificationRepo notificationRepo, ObjectMapper objectMapper) {
		this.notificationRepo = notificationRepo;
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
		this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	@KafkaListener(topics = "txn-initiated", groupId = "notification-group")
	public void listener(String message) throws JsonProcessingException {
		Transaction transaction = objectMapper.readValue(message, Transaction.class);
		log.debug("listener:: Deserialized Transaction: {}",transaction);
		
		String notificationMessage = "$" + transaction.getAmount() + "Received from : " + transaction.getSenderId();
		log.debug("listener:: notificationMessage: {}",notificationMessage);
		
		Notification notification = Notification.builder()
				.message(notificationMessage).createdDate(LocalDateTime.now())
				.userId(transaction.getReceiverId())
				.build();
		
		log.debug("listener:: Saving notification in DB");
		notificationRepo.save(notification);
	}

}
