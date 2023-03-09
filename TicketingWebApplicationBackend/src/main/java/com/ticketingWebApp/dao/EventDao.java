package com.ticketingWebApp.dao;

import com.ticketingWebApp.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDao {

	private Integer eventId;
	private String eventTitle;
	private Integer price;
	private Integer quantity;
	private User user;
}
