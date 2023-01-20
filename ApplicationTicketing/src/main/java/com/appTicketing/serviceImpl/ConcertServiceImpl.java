package com.appTicketing.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appTicketing.dao.ConcertDao;
import com.appTicketing.entity.Concert;
import com.appTicketing.exceptionalHandling.SourceUnAvailable;
import com.appTicketing.repository.ConcertRepository;
import com.appTicketing.service.ConcertService;

@Service
public class ConcertServiceImpl implements ConcertService {

	@Autowired
	private ConcertRepository concertRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ConcertDao createConcert(ConcertDao concertDao) {
		Concert concert = this.modelMapper.map(concertDao, Concert.class);
		Concert addConcert = this.concertRepository.save(concert);
		return this.modelMapper.map(addConcert, ConcertDao.class);
	}

	@Override
	public ConcertDao updateConcert(ConcertDao concertDao, Integer concertId) {
		Concert concert = this.concertRepository.findById(concertId)
				.orElseThrow(() -> new SourceUnAvailable("Concert", "concert id", concertId));
		concert.setConcertTitle(concertDao.getConcertTitle());
		concert.setPrice(concertDao.getPrice());
		concert.setCapacity(concertDao.getCapacity());
		Concert updatedConcert = this.concertRepository.save(concert);
		return this.modelMapper.map(updatedConcert, ConcertDao.class);
	}

	@Override
	public ConcertDao getConcertById(Integer concertId) {
		Concert concert = this.concertRepository.findById(concertId)
				.orElseThrow(() -> new SourceUnAvailable("Conert", "concert id", concertId));
		return this.modelMapper.map(concert, ConcertDao.class);
	}

	@Override
	public List<ConcertDao> concertList() {
		List<Concert> concerts = this.concertRepository.findAll();
		List<ConcertDao> concertDaos = concerts.stream()
				.map((concert) -> this.modelMapper.map(concert, ConcertDao.class)).collect(Collectors.toList());
		return concertDaos;
	}

}
