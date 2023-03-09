package com.ticketingWebApp.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingWebApp.dao.PaymentDao;
import com.ticketingWebApp.entity.Order;
import com.ticketingWebApp.entity.Payment;
import com.ticketingWebApp.repository.OrderRepository;
import com.ticketingWebApp.repository.PaymentRepository;
import com.ticketingWebApp.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PaymentDao saveDetails(PaymentDao paymentDao, Integer orderId) {
		Order order = this.orderRepository.findById(orderId).get();
		Payment payment = this.modelMapper.map(paymentDao, Payment.class);
		payment.setOrder(order);
		Payment addPayment = this.paymentRepository.save(payment);
		PaymentDao addPaymentDao = this.modelMapper.map(addPayment, PaymentDao.class);
		return addPaymentDao;
	}

	@Override
	public PaymentDao getDetails(Integer paymentId) {
		Payment payment = this.paymentRepository.findById(paymentId).get();
		PaymentDao paymentDao = this.modelMapper.map(payment, PaymentDao.class); 
		return paymentDao;
			}
	
	
}
