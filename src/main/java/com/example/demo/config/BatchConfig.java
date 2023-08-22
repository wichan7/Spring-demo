package com.example.demo.config;

import java.text.ParseException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
	
	@Bean
	public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
	    return new JobBuilder("sampleJob", jobRepository)
	                .start(sampleStep)
	                .build();
	}

	@Bean
	public Step sampleStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("sampleStep", jobRepository)
					.<String, String>chunk(10, transactionManager)
					.reader(itemReader())
					.writer(itemWriter())
					.build();
	}
	
    @Bean
    public ItemReader<String> itemReader(){
        return new ItemReader<String>() {
            @Override
            public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                return "Read OK";
            }
        };
    }
    
    @Bean
    public ItemWriter<String> itemWriter(){
        return strList -> {
            strList.forEach(
                    str -> log.info("str: {}", str)
            );
        };
    }
}