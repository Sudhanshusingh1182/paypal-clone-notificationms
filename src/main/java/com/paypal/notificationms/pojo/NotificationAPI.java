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
public class NotificationAPI {
	private Long id;
	private String userId;
	private String message;
	private LocalDateTime createdDate;
}
