package it.hbg.springbatchtest;

import org.springframework.batch.item.ItemProcessor;

public class DummyItemProcessor implements ItemProcessor<String,String> {

    @Override
    public String process(String item) throws Exception {
        
        return item + "AAA";
    }
    
}