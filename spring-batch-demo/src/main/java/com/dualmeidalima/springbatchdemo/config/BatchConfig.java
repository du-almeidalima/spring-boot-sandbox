package com.dualmeidalima.springbatchdemo.config;

import com.dualmeidalima.springbatchdemo.UserWorkedHoursDTO;
import com.dualmeidalima.springbatchdemo.model.Payment;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    public Job job(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<UserWorkedHoursDTO> itemReader,
            ItemProcessor<UserWorkedHoursDTO, Payment> itemProcessor,
            ItemWriter<Payment> itemWriter
    ) {
        var step = stepBuilderFactory.get("ETL-File_Load")
                // Batch Size, process 100 per time
                .<UserWorkedHoursDTO, Payment>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        // Creating a Job
        return jobBuilderFactory.get("ETL-Job")
                // Incrementer for differentiating Jobs, this creates a Id for each Job Run
                .incrementer(new RunIdIncrementer())
                // For multiple steps, use Flow
                .start(step)
                .build();
    }
}
