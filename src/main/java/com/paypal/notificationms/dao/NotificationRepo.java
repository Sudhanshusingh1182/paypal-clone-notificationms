package com.paypal.notificationms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.notificationms.entity.Notification;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
	
	List<Notification> findByUserId(String userId);
}
