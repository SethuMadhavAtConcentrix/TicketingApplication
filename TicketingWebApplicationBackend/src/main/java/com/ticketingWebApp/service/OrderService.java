package com.ticketingWebApp.service;

import java.util.List;

import com.ticketingWebApp.dao.OrderDao;

public interface OrderService {
	
	public List<OrderDao> viewMyOrders();
	public OrderDao placeOrder(OrderDao orderDao, Integer eventId, Integer userId);
	public OrderDao viewOrderById(Integer orderId);
	

}
