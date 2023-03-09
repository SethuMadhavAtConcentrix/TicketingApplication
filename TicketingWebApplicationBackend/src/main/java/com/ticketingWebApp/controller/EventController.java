package com.ticketingWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingWebApp.dao.EventDao;
import com.ticketingWebApp.service.EventService;

@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@PostMapping("/users/{userId}/add")
	public ResponseEntity<EventDao> addEvent(@PathVariable Integer userId, @RequestBody EventDao eventDao) {
		EventDao addEvent = this.eventService.createEvent(eventDao, userId);
		return new ResponseEntity<EventDao>(addEvent,HttpStatus.CREATED);
	}
	@GetMapping("/events/{eventId}")
	public ResponseEntity<EventDao> getEventById(@PathVariable Integer eventId){
		EventDao eventById = this.eventService.viewEventById(eventId);
		return new ResponseEntity<EventDao>(eventById,HttpStatus.OK);
	}
	@GetMapping("/events")
	public ResponseEntity<List<EventDao>> getAllEvents(){
		List<EventDao> eventList = this.eventService.viewAllEvents();
		return new ResponseEntity<List<EventDao>>(eventList,HttpStatus.OK);
	}
	
	@PutMapping("/users/{userId}/events/{eventId}/update")
	public ResponseEntity<EventDao> updateEvent(@PathVariable Integer userId, @PathVariable Integer eventId, @RequestBody EventDao eventDao) {
		EventDao eventUpdation = this.eventService.updateEvent(eventDao, eventId, userId);
		return new ResponseEntity<EventDao>(eventUpdation,HttpStatus.OK);
	}
	
	
}
