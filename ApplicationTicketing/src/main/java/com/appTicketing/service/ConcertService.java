package com.appTicketing.service;

import java.util.List;

import com.appTicketing.dao.ConcertDao;

public interface ConcertService {
	
	public ConcertDao createConcert(ConcertDao concertDao);
	public ConcertDao updateConcert(ConcertDao concertDao, Integer concertId);
	public ConcertDao getConcertById(Integer concertId);
	public List<ConcertDao>	concertList();
	

}
