package com.apalya.mmc.scheduleIngest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.apalya.mmc.scheduleIngest.service.SchedulePublishService;

@Configuration
@Component
@EnableScheduling
public class ScheduleConfig {
	
	@Autowired
	SchedulePublishService schedulePublishService;
	
	private final Logger log = LoggerFactory.getLogger(SchedulePublishService.class);
	
	    @Bean
	    public static ThreadPoolTaskScheduler  threadPoolTaskScheduler(){
	        ThreadPoolTaskScheduler threadPoolTaskScheduler
	          = new ThreadPoolTaskScheduler();
	        threadPoolTaskScheduler.setPoolSize(5);
	        threadPoolTaskScheduler.setThreadNamePrefix(
	          "ThreadPoolTaskScheduler");
	        threadPoolTaskScheduler.initialize();
	        return threadPoolTaskScheduler;
	    }
	    
	    @Scheduled (cron = "0 0 * * * *")
	    public void checkAndPublish(){
			log.info("In Schedule and Publish...");
			schedulePublishService.findSchedule();	
			log.info("Scheduled publish process end...");
			
		}
	}

