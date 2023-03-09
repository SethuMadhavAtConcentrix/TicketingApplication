package com.ticketingWebApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingWebApp.dao.OrderDao;
import com.ticketingWebApp.entity.Event;
import com.ticketingWebApp.entity.Order;
import com.ticketingWebApp.repository.EventRepository;
import com.ticketingWebApp.repository.OrderRepository;
import com.ticketingWebApp.service.OrderService;
import com.ticketingWebApp.user.entity.User;
import com.ticketingWebApp.user.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<OrderDao> viewMyOrders() {
		List<Order> allOrders = this.orderRepository.findAll();
		List<OrderDao> allOrdersDao = allOrders.stream()
				.map((allOrder) -> this.modelMapper.map(allOrder, OrderDao.class)).collect(Collectors.toList());
		return allOrdersDao;
	}

	@Override
	public OrderDao placeOrder(OrderDao orderDao, Integer eventId, Integer userId) {
		User user = this.userRepository.findById(userId).get();
		Event eventSelection = this.eventRepository.findById(eventId).get();
		eventSelection.setUser(user);
		Order order = this.modelMapper.map(orderDao, Order.class);
		order.setEvent(eventSelection);
		Order newOrder = this.orderRepository.save(order);
		OrderDao newOrderDao = this.modelMapper.map(newOrder, OrderDao.class);
		return newOrderDao;
	}

	@Override
	public OrderDao viewOrderById(Integer orderId) {
		Order order = this.orderRepository.findById(orderId).get();
		OrderDao orderDao = this.modelMapper.map(order, OrderDao.class);
		return orderDao;
	}

}
