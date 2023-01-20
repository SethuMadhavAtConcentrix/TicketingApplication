package com.appTicketing.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appTicketing.dao.PaymentDao;
import com.appTicketing.entity.Order;
import com.appTicketing.entity.Payment;
import com.appTicketing.exceptionalHandling.SourceUnAvailable;
import com.appTicketing.repository.OrderRepository;
import com.appTicketing.repository.PaymentRepository;
import com.appTicketing.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PaymentDao makePayment(PaymentDao paymentDao, Integer orderId) {
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new SourceUnAvailable("Order", "order id", orderId));
		Payment payment = this.modelMapper.map(paymentDao, Payment.class);
		payment.setOrder(order);
		Payment paymentMaking = this.paymentRepository.save(payment);
		return this.modelMapper.map(paymentMaking, PaymentDao.class);
	}

	@Override
	public PaymentDao getPaymentDetail(Integer paymentId) {
		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new SourceUnAvailable("Payment", "payment id", paymentId));
		return this.modelMapper.map(payment, PaymentDao.class);
	}

}
