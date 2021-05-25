package com.techgeeknext.springbootbatchlistener.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBatchJobController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job airportInfoJob;

    /**
     * Method to launch the batch job to read the airport details from input csv file and write to output text file
     * @return String
     * @throws Exception
     */
    @RequestMapping("/launch/airport/job")
    public String launchJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(airportInfoJob, jobParameters);
        return "Airport Job has been invoked from TechGeekNext!!!";
    }
}