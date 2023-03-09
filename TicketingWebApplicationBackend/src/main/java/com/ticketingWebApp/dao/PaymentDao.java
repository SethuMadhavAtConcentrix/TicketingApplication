package com.ticketingWebApp.dao;


import com.ticketingWebApp.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDao {
	private Integer paymentId;
	private String status;
	private Long cardno;
	private Integer expiryMonth;
	private Integer expiryYear;
	private Order order;
}

