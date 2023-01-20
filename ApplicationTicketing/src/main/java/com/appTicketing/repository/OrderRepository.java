package com.appTicketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appTicketing.entity.Order;
import com.appTicketing.entity.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findOrdersByUser(User user);
	//Order getOrderIdByUserId(User user,Order order);
	
}
