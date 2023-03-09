package com.ticketingWebApp.service;

import com.ticketingWebApp.dao.PaymentDao;

public interface PaymentService {
	
	public PaymentDao saveDetails(PaymentDao paymentDao, Integer orderId);
	public PaymentDao getDetails(Integer paymentId);

}
