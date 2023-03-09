package com.ticketingWebApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ticketingWebApp.dao.EventDao;
import com.ticketingWebApp.entity.Event;
import com.ticketingWebApp.exceptionalHandling.SourceUnAvailable;
import com.ticketingWebApp.repository.EventRepository;
import com.ticketingWebApp.service.EventService;
import com.ticketingWebApp.user.entity.User;
import com.ticketingWebApp.user.repository.UserRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EventDao> viewAllEvents() {
		List<Event> allEvents = this.eventRepository.findAll();
		List<EventDao> allEventDaos = allEvents.stream().map((event) -> this.modelMapper.map(event, EventDao.class))
				.collect(Collectors.toList());
		return allEventDaos;
	}

	@Override
	public EventDao viewEventById(Integer eventId) {
		Event event = this.eventRepository.findById(eventId).get();
		EventDao eventDao = this.modelMapper.map(event, EventDao.class);
		return eventDao;
	}

	@Override
	public EventDao createEvent(EventDao eventDao, Integer userId) {
		User user = this.userRepository.findById(userId).get();
		Event event = this.modelMapper.map(eventDao, Event.class);
		event.setUser(user);
		Event newEvent = this.eventRepository.save(event);
		EventDao newEventDao = this.modelMapper.map(newEvent, EventDao.class);
		return newEventDao;
	}

	@Override
	public EventDao updateEvent(EventDao eventDao, Integer eventId, Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new SourceUnAvailable("User", "user id", userId));
		Event event = this.modelMapper.map(eventDao, Event.class);
		event.setUser(user);
		Event updateEvent = this.eventRepository.findById(eventId)
				.orElseThrow(() -> new SourceUnAvailable("Event", "event id", eventId));
		updateEvent.setPrice(eventDao.getPrice());
		Event updatedEvent = this.eventRepository.save(updateEvent);
		EventDao updatedEventDao = this.modelMapper.map(updatedEvent, EventDao.class);
		return updatedEventDao;
	}

}
