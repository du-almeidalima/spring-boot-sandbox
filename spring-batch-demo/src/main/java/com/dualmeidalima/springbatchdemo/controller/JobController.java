package com.dualmeidalima.springbatchdemo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JobController {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public JobController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
    public ResponseEntity<String> start() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException {

        var jobParameters = new JobParameters(
                Map.of("time", new JobParameter(System.currentTimeMillis()))
        );

        var jobExecution = this.jobLauncher.run(job, jobParameters);

        System.out.println("JobExecution >> " + jobExecution.getStatus());
        System.out.println("JobExecution >> Batch is running");

        while (jobExecution.isRunning()) {
            System.out.println("...");
        }

        return ResponseEntity.ok(jobExecution.getStatus().toString());
    }
}
