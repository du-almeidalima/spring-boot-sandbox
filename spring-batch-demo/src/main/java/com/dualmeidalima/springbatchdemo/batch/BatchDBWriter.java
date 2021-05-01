package com.dualmeidalima.springbatchdemo.batch;

import com.dualmeidalima.springbatchdemo.model.Payment;
import com.dualmeidalima.springbatchdemo.repository.PaymentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchDBWriter implements ItemWriter<Payment> {

    private final PaymentRepository paymentRepository;

    public BatchDBWriter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void write(List<? extends Payment> usersPaymentList) throws Exception {
        System.out.println("BatchDBWriter >> Saving payments to DataBase");

        this.paymentRepository.saveAll(usersPaymentList);
    }
}
