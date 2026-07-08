package com.hotelplatform.paymentservice.repository;

import com.hotelplatform.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}