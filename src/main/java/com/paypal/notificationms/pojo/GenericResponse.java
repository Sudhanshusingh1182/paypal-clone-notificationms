package com.paypal.notificationms.pojo;

import java.util.List;

import com.paypal.notificationms.error.ErrorDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
	private Boolean success;
	private List<ErrorDetail> errorDetailList;
}
