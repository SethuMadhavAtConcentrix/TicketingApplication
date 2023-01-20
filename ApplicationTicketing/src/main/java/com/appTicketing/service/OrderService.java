package com.appTicketing.service;

import java.util.List;

import com.appTicketing.dao.OrderDao;

public interface OrderService {
	
	public OrderDao placeOrder(OrderDao orderDao, Integer userId, Integer concertId);
	//public OrderDao getOrderById(Integer orderId, Integer userId);
	public List<OrderDao> findAllOrdersByUser(Integer userId);

}
