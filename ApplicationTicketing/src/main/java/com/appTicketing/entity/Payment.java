package com.appTicketing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer cardNo;
	
	private Integer expiryMonth;
	
	private Integer expiryYear;
	
	private String status;
	
	@OneToOne
	private Order order;

}
