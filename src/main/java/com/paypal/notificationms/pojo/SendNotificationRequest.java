package com.paypal.notificationms.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendNotificationRequest {
	private Long id;
	private Long userId;
	private String message;
	private LocalDateTime createdDate;
}
