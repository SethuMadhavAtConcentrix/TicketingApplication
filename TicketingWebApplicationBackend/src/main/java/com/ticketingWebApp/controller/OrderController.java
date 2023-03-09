package com.ticketingWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingWebApp.dao.OrderDao;
import com.ticketingWebApp.service.OrderService;

@RestController
@RequestMapping("/api/users/{userId}")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/events/{eventId}/order")
	public ResponseEntity<OrderDao> saveOrders(@PathVariable Integer userId, @PathVariable Integer eventId, @RequestBody OrderDao orderDao) {
		OrderDao saveOrder = this.orderService.placeOrder(orderDao, eventId, userId);
		return new ResponseEntity<OrderDao>(saveOrder,HttpStatus.CREATED);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<List<OrderDao>> myOrders(){
		List<OrderDao> orders = this.orderService.viewMyOrders();
		return new ResponseEntity<List<OrderDao>>(orders,HttpStatus.OK);
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<OrderDao> viewOrderById(@PathVariable Integer orderId) {
		OrderDao orderById = this.orderService.viewOrderById(orderId);
		return new ResponseEntity<OrderDao>(orderById,HttpStatus.OK);
	}
	
	

}
