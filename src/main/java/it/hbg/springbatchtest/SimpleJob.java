package it.hbg.springbatchtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.item.support.ListItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleJob {
    
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
   

    private final List<String> tipiScrittureOlg = new ArrayList<>(Arrays.asList(
        "T", // win total
        "R", // win real
        "B" // bonus
    ));
    
    @Bean
    @StepScope
    public IteratorItemReader<String> elencoTipoScrittureOlgReader() throws Exception {        
        IteratorItemReader<String> reader = new IteratorItemReader<String>(tipiScrittureOlg);
        return reader;
    }

    
    /*
     *  job
     */

    @Bean
    public Job testJob(Step testStep, Step pdfEsercenteIRIRAwpStep, Step pdfLiquidazioneGestoreAwpStep) {
    
        return jobBuilderFactory.get("testJob")
            .flow(testStep)      
            .end()            
            .build();
    }

    /*
     *  steps
     */

    @Bean
    public Step testStep() throws Exception {
        return stepBuilderFactory.get("testStep")
            .<String, String> chunk(1)            
            .reader(elencoTipoScrittureOlgReader()) 
            .processor(new DummyItemProcessor())
            .writer(new DummyItemWriter())
            .allowStartIfComplete(true)
            .build();
    }

}