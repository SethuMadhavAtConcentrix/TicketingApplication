package com.appTicketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appTicketing.entity.Concert;

public interface ConcertRepository extends JpaRepository<Concert, Integer>{
	

}
