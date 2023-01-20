package com.appTicketing.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Integer orderId;
	private Integer quantity;
	private Integer amount;
	
	@ManyToOne
	@JoinColumn(name = "concert_id")
	private Concert concert;
	
	@ManyToOne
	private User user;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

}
