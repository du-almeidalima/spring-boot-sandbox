package com.dualmeidalima.springbatchdemo.controller;

import com.dualmeidalima.springbatchdemo.model.Payment;
import com.dualmeidalima.springbatchdemo.repository.PaymentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    List<Payment> getPayments() {
        return this.paymentRepository.findAll();
    }
}
