package com.ticketingWebApp.dao;

import com.ticketingWebApp.entity.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDao {
	private Integer orderId;
	private Integer noOfTickets;
	private Integer amount;
	private Event event;
}

