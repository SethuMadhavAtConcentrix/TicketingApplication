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
@Table(name="tbl_payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_id")
	private Integer paymentId;
	
	@Column(name = "card_no")
	private Long cardno;
	
	@Column(name = "expiry_month")
	private Integer expiryMonth;
	
	@Column(name = "expiry_year")
	private Integer expiryYear;
	
	private String status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "pay_order", joinColumns = @JoinColumn(name = "payment_id"),
	inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Order order;

}

