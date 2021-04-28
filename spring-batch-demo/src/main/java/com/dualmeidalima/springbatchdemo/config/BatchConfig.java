package com.dualmeidalima.springbatchdemo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        var step = stepBuilderFactory.get("ETL-File_Load")
                // Batch Size, process 100 per time
                .chunk(100)
                .reader()
                .processor()
                .writer()
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
