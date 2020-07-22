package it.hbg.springbatchtest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBatchTest
@ExtendWith(SpringExtension.class)
@EnableBatchProcessing
@ContextConfiguration(classes={SimpleJob.class, BatchConfig.class, DatabaseConfig.class})
public class SimpleJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
  
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;    
    
    @Test
    public void sapScrittureContabiliJobSuccessTest() throws Exception {

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        ExitStatus actualJobExitStatus = jobExecution.getExitStatus();
     
        // then
        assertEquals("COMPLETED", actualJobExitStatus.getExitCode());
    }    
}