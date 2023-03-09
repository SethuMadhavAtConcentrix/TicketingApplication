package com.ticketingWebApp.service;

import java.util.List;

import com.ticketingWebApp.dao.EventDao;

public interface EventService {
	
	public List<EventDao> viewAllEvents();
	public EventDao viewEventById(Integer eventId);
	public EventDao createEvent(EventDao eventDao, Integer userId);
	public EventDao updateEvent(EventDao eventDao, Integer eventId, Integer userId);
	
	
	

}
