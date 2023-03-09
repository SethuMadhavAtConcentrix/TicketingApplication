package com.ticketingWebApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketingWebApp.entity.Event;
import com.ticketingWebApp.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	List<Order> getByEvent(Event event);

}
