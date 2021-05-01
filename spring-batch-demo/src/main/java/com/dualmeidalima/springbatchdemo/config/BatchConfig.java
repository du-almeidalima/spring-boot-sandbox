package com.dualmeidalima.springbatchdemo.config;

import com.dualmeidalima.springbatchdemo.dto.UserWorkedHoursDTO;
import com.dualmeidalima.springbatchdemo.model.Payment;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {


    /*
    All batch processing can be described in its most simple form as reading in large amounts of data,
    performing some type of calculation or transformation, and writing the result out.
    Spring Batch provides three key interfaces to help perform bulk reading and writing:
        - ItemReader
        - ItemProcessor
        - ItemWriter.
     */
    @Bean
    public Job job(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<UserWorkedHoursDTO> itemReader,
            ItemProcessor<UserWorkedHoursDTO, Payment> itemProcessor,
            ItemWriter<Payment> itemWriter
    ) {
        // This creates a step for the Job, each part of this step is going to be implemented below
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

    @Bean
    public FlatFileItemReader<UserWorkedHoursDTO> itemReader(
            LineMapper<UserWorkedHoursDTO> lineMapper
    ) {
        var flatFileItemReader = new FlatFileItemReader<UserWorkedHoursDTO>();
        flatFileItemReader.setResource(new ClassPathResource("users-demo.csv"));
        flatFileItemReader.setName("USER_WORKED_HOURS-READER");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper);

        return flatFileItemReader;
    }

    // This LineMapper is what transforms the CSV line into a POJO
    @Bean
    public LineMapper<UserWorkedHoursDTO> lineMapper() {
        var defaultLineMapper = new DefaultLineMapper<UserWorkedHoursDTO>();
        var lineTokenizer = new DelimitedLineTokenizer();

        // Configuring how to read the CSV
        lineTokenizer.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        lineTokenizer.setStrict(false);
        // Test removing
        lineTokenizer.setNames("userId", "hoursWorked");

        // Configuring the Mapper
        // The BeanWrapperFieldSetMapper automates the mapping of a FieldSet
        var fieldSetMapper = new BeanWrapperFieldSetMapper<UserWorkedHoursDTO>();
        fieldSetMapper.setTargetType(UserWorkedHoursDTO.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
