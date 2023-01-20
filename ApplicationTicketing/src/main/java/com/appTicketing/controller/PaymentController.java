package com.appTicketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appTicketing.dao.PaymentDao;
import com.appTicketing.service.PaymentService;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/api")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/order/{orderId}/pay")
	public ResponseEntity<PaymentDao> doPayment(@PathVariable Integer orderId, @RequestBody PaymentDao paymentDao){
		PaymentDao pay = this.paymentService.makePayment(paymentDao, orderId);
		return new ResponseEntity<PaymentDao>(pay,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/payments/{paymentId}")
	public ResponseEntity<PaymentDao> getPaymentById(@PathVariable Integer paymentId) {
		PaymentDao paymentDao = this.paymentService.getPaymentDetail(paymentId);
		return new ResponseEntity<PaymentDao>(paymentDao, HttpStatus.OK);
	}

}
