package com.dualmeidalima.springbatchdemo.repository;

import com.dualmeidalima.springbatchdemo.model.Payment;
import com.dualmeidalima.springbatchdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByUser(User user);
}
