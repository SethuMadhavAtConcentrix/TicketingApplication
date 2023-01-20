package com.appTicketing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appTicketing.dao.ConcertDao;
import com.appTicketing.service.ConcertService;

@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/api/concert")
public class ConcertController {

	@Autowired
	private ConcertService concertService;

	@PostMapping("/create")
	public ResponseEntity<ConcertDao> createConcert(@RequestBody ConcertDao concertDao) {
		ConcertDao createConcert = this.concertService.createConcert(concertDao);
		return new ResponseEntity<ConcertDao>(createConcert, HttpStatus.CREATED);
	}

	@PutMapping("/update/{concertId}")
	public ResponseEntity<ConcertDao> updateConcert(@PathVariable Integer concertId,
			@RequestBody ConcertDao concertDao) {
		ConcertDao updateConcert = this.concertService.updateConcert(concertDao, concertId);
		return new ResponseEntity<ConcertDao>(updateConcert, HttpStatus.CREATED);
	}

	@GetMapping("/{concertId}")
	public ResponseEntity<ConcertDao> getConcertById(@PathVariable Integer concertId) {
		ConcertDao concertDao = this.concertService.getConcertById(concertId);
		return new ResponseEntity<ConcertDao>(concertDao, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ConcertDao>> listConcerts() {
		List<ConcertDao> concerts = this.concertService.concertList();
		return ResponseEntity.ok(concerts);
	}

}
