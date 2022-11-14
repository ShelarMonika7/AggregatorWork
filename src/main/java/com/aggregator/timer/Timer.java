package com.aggregator.timer;

import com.aggregator.service.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class Timer implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(Timer.class);

    LocalDateTime end;
    ProcessingService processingService;
    boolean execute;

    public Timer(ProcessingService processingService) {
        this.processingService = processingService;
        end = LocalDateTime.now().plusSeconds(15);
    }

    @Override
    public void run() {
    	this.execute = true;
        while (this.execute) {

            while (LocalDateTime.now().isAfter(end)) {
                logger.debug("ThreadId: {}, Timer threshold reached for {}",String.valueOf(Thread.currentThread().getId()), this.processingService.getClass().getName());      
                execute = this.processingService.process(this.processingService.getQueue());
                reset();
                break;
            }
        }
    }

    public void reset() {
        end = LocalDateTime.now().plusSeconds(15);
    }
}
