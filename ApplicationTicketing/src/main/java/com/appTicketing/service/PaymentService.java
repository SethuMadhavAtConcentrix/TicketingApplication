package com.appTicketing.service;

import com.appTicketing.dao.PaymentDao;

public interface PaymentService {
	
	public PaymentDao makePayment(PaymentDao paymentDao, Integer orderId);
	public PaymentDao getPaymentDetail(Integer paymentId);

}
