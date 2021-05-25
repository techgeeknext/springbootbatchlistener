package com.techgeeknext.springbootbatchlistener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class SpringBatchJobExecutionListener implements JobExecutionListener {

    Logger logger = LoggerFactory.getLogger(SpringBatchJobExecutionListener.class);

    public void beforeJob(JobExecution jobExecution) {

        logger.info("AirportJobExecutionListener - beforeJob started.");
    }

    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("AirportJobExecutionListener - - afterJob completed successfully");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.info("AirportJobExecutionListener - afterJob failed.");
        }
    }
}
