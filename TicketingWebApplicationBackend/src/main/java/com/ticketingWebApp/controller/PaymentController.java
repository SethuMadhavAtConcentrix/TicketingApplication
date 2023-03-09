package com.ticketingWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingWebApp.dao.PaymentDao;
import com.ticketingWebApp.service.PaymentService;

@RestController
@RequestMapping("/api/users/{userId}")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/orders/{orderId}/payment")
	public ResponseEntity<PaymentDao> makePayment(@PathVariable Integer orderId, @RequestBody PaymentDao paymentDao) {
		PaymentDao addPayment = this.paymentService.saveDetails(paymentDao, orderId);
		return new ResponseEntity<PaymentDao>(addPayment,HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{orderId}/{paymentId}")
	public ResponseEntity<PaymentDao> findPayment(@PathVariable Integer paymentId) {
		PaymentDao paymentById = this.paymentService.getDetails(paymentId);
		return new ResponseEntity<PaymentDao>(paymentById,HttpStatus.OK);
	}

}
