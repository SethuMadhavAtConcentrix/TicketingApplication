package com.appTicketing.controller;

import java.util.List;

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

import com.appTicketing.dao.OrderDao;
import com.appTicketing.service.OrderService;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/users/{userId}/concerts/{concertId}/add")
	public ResponseEntity<OrderDao> createOrder(
			@RequestBody OrderDao orderDao,
			@PathVariable Integer userId,
			@PathVariable Integer concertId 
			){
		OrderDao createOrder = this.orderService.placeOrder(orderDao, userId, concertId);
		return new ResponseEntity<OrderDao>(createOrder,HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userId}/myorders")
	public ResponseEntity<List<OrderDao>> getOrdersbyUser(@PathVariable Integer userId){
		List<OrderDao> orders = this.orderService.findAllOrdersByUser(userId);
		return new ResponseEntity<List<OrderDao>>(orders, HttpStatus.OK);
	}
	
	/*@GetMapping("/myorders/{orderId}")
	public ResponseEntity<OrderDao> fetchOrderbyUser(@PathVariable Integer userId, @PathVariable Integer orderId){
		OrderDao order = this.orderService.getOrderById(orderId, userId);
		return new ResponseEntity<OrderDao>(order, HttpStatus.OK);
	}*/
	
	

}
