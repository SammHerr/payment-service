package com.hotelplatform.paymentservice.service;

import com.hotelplatform.paymentservice.dto.PaymentDTO;
import com.hotelplatform.paymentservice.entity.Payment;
import com.hotelplatform.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));

        return toDTO(payment);
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = toEntity(paymentDTO);

        payment.setId(null);
        payment.setStatus("PENDING");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setActive(true);

        return toDTO(paymentRepository.save(payment));
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));

        payment.setReservationId(paymentDTO.getReservationId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(paymentDTO.getStatus());
        payment.setTransactionReference(paymentDTO.getTransactionReference());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setActive(paymentDTO.getActive());

        return toDTO(paymentRepository.save(payment));
    }

    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con ID: " + id));

        payment.setActive(false);
        paymentRepository.save(payment);
    }

    private PaymentDTO toDTO(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .reservationId(payment.getReservationId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .transactionReference(payment.getTransactionReference())
                .paymentDate(payment.getPaymentDate())
                .active(payment.getActive())
                .build();
    }

    private Payment toEntity(PaymentDTO paymentDTO) {
        return Payment.builder()
                .id(paymentDTO.getId())
                .reservationId(paymentDTO.getReservationId())
                .amount(paymentDTO.getAmount())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .status(paymentDTO.getStatus())
                .transactionReference(paymentDTO.getTransactionReference())
                .paymentDate(paymentDTO.getPaymentDate())
                .active(paymentDTO.getActive())
                .build();
    }
}