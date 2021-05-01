package com.dualmeidalima.springbatchdemo.controller;

import com.dualmeidalima.springbatchdemo.model.Payment;
import com.dualmeidalima.springbatchdemo.model.User;
import com.dualmeidalima.springbatchdemo.repository.PaymentRepository;
import com.dualmeidalima.springbatchdemo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public UserController(UserRepository userRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Integer userId) {
        var userOptional = this.userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userOptional.get());
    }

    @GetMapping("/{userId}/payments")
    public ResponseEntity<List<Payment>> getUserPayments(@PathVariable Integer userId) {
        var userOptional = this.userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this.paymentRepository.findAllByUser(userOptional.get()));
    }
}
