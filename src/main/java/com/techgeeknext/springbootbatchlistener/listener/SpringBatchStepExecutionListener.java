package com.techgeeknext.springbootbatchlistener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class SpringBatchStepExecutionListener implements StepExecutionListener {

    Logger logger = LoggerFactory.getLogger(SpringBatchStepExecutionListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution)
    {
        logger.info("AirportStepListener - called before step.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.info("AirportStepListener - called after step.");
        //ExitStatus indicate that step has completed
        return ExitStatus.COMPLETED;
    }
}
