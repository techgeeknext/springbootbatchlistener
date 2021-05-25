package com.techgeeknext.springbootbatchlistener;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SpringBootBatchExampleApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootBatchExampleApplication.class, args);
	}

}
