package com.appTicketing.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDao {
	
	private Integer orderId;
	private Integer quantity;
	private Integer amount;
	private UserDao user;
	private ConcertDao concert;
	private PaymentDao payment;

}
