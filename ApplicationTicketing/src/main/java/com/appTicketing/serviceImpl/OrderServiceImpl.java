package com.appTicketing.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appTicketing.dao.OrderDao;
import com.appTicketing.entity.Concert;
import com.appTicketing.entity.Order;
import com.appTicketing.entity.User;
import com.appTicketing.exceptionalHandling.SourceUnAvailable;
import com.appTicketing.repository.ConcertRepository;
import com.appTicketing.repository.OrderRepository;
import com.appTicketing.repository.UserRepository;
import com.appTicketing.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConcertRepository concertRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OrderDao placeOrder(OrderDao orderDao, Integer userId, Integer concertId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new SourceUnAvailable("User", "user id", userId));
		Concert concert = this.concertRepository.findById(concertId)
				.orElseThrow(() -> new SourceUnAvailable("Concert", "concert id", concertId));
		Order order = this.modelMapper.map(orderDao, Order.class);
		order.setUser(user);
		order.setConcert(concert);
		Order newOrder = this.orderRepository.save(order);
		return this.modelMapper.map(newOrder, OrderDao.class);
	}

	/*@Override
	public OrderDao getOrderById(Integer orderId, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new SourceUnAvailable("User", "user id", userId));
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new SourceUnAvailable("Order", "order id", orderId));
		Order getOrder = this.orderRepository.getOrderIdByUserId(user, order);
		OrderDao orderDao = this.modelMapper.map(getOrder, OrderDao.class);
		return orderDao;
	}*/

	@Override
	public List<OrderDao> findAllOrdersByUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new SourceUnAvailable("User", "user id", userId));
		List<Order> orders = this.orderRepository.findOrdersByUser(user);
		List<OrderDao> orderDaos = orders.stream().map((order) -> this.modelMapper.map(order, OrderDao.class))
				.collect(Collectors.toList());
		return orderDaos;
	}

}
