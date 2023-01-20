package com.appTicketing.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDao {
	
	private Integer paymentId;
	private Integer cardNo;
	private Integer expiryMonth;
	private Integer expiryYear;
	private String status;

}
