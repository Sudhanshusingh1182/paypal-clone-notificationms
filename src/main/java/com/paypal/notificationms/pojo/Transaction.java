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
public class Transaction {
	private Long id;
	private Long senderId;
	private Long receiverId;
	private Double amount;
	private LocalDateTime createdDate;
	private String status;
}