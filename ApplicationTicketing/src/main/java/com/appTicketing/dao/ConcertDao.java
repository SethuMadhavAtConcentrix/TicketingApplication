package com.appTicketing.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConcertDao {
	
	private Integer concertId;
	private String concertTitle;
	private Integer price;
	private Integer capacity;

}
