package com.appTicketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appTicketing.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
