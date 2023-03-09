package com.ticketingWebApp.entity;

import com.ticketingWebApp.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_events")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "event_id")
	private Integer eventId;
	
	@Column(name = "event_title")
	private String eventTitle;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "event_user", joinColumns = @JoinColumn(name = "event_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;

}
