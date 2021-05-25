package com.techgeeknext.springbootbatchlistener.config;

import com.techgeeknext.springbootbatchlistener.listener.SpringBatchJobExecutionListener;
import com.techgeeknext.springbootbatchlistener.listener.SpringBatchStepExecutionListener;
import com.techgeeknext.springbootbatchlistener.model.AirportInfo;
import com.techgeeknext.springbootbatchlistener.step.AirportInfoProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * Spring Batch Configuration Class
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job airportInfoJob() {
		return jobBuilderFactory.get("airportInfoJob")
				.incrementer(new RunIdIncrementer())
				.listener(new SpringBatchJobExecutionListener())
				.flow(airportInfoStep())
                .end()
                .build();
	}

    @Bean
    public Step airportInfoStep() {
        return stepBuilderFactory.get("airportInfoStep")
                .listener(new SpringBatchStepExecutionListener())
                .<AirportInfo, String>chunk(10)
                .reader(airportInfoItemReader())
                .processor(airportInfoProcessor())
                .writer(airportInfoItemWriter())
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }

    @Bean
    public FlatFileItemReader<AirportInfo> airportInfoItemReader() {
        return new FlatFileItemReaderBuilder<AirportInfo>()
                .name("airportInfoItemReader")
                .resource(new ClassPathResource("csv/airports.csv"))
                .delimited()
                .names(new String[] {"code", "airport","city","state","country"})
                .targetType(AirportInfo.class)
                .build();
    }

    @Bean
    public AirportInfoProcessor airportInfoProcessor(){
	    return new AirportInfoProcessor();
    }

    @Bean
    public FlatFileItemWriter<String> airportInfoItemWriter() {
        return new FlatFileItemWriterBuilder<String>()
                .name("airportInfoItemWriter")
                .resource(new FileSystemResource(
                        "csv/airport-output.txt"))
                .lineAggregator(new PassThroughLineAggregator<>())
				.build();
    }
}
