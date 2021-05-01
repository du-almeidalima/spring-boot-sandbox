package com.dualmeidalima.springbatchdemo.batch;

import com.dualmeidalima.springbatchdemo.dto.UserWorkedHoursDTO;
import com.dualmeidalima.springbatchdemo.model.Payment;
import com.dualmeidalima.springbatchdemo.repository.UserRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessor implements ItemProcessor<UserWorkedHoursDTO, Payment> {

    private final UserRepository userRepository;

    @Autowired
    public BatchProcessor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Payment process(UserWorkedHoursDTO userWorkedHours) throws Exception {
        var userOptional = this.userRepository.findById(userWorkedHours.getUserId());
        var userPayment = new Payment();

        if (userOptional.isPresent()) {
            var user = userOptional.get();

            userPayment.setUser(user);
            userPayment.setAmount(user.getRole().getHourly() * userWorkedHours.getHoursWorked());

            System.out.println(
                    "BatchProcessor >> Processing User: " + user.getName() + "Hours. Amount: " + userPayment.getAmount()
            );

            return userPayment;
        }

        System.out.println("BatchProcessor >> User ID: " + userWorkedHours.getUserId() + " not found.");
        return null;
    }
}
