package com.ticketingWebApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer orderId;
	
	private Integer noOfTickets;
	
	private Integer amount;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "order_event", joinColumns = @JoinColumn(name = "order_id"),
	inverseJoinColumns = @JoinColumn(name = "event_id"))
	private Event event;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinTable(name = "order_user", joinColumns = @JoinColumn(name = "order_id"),
//	inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private User user;

}
