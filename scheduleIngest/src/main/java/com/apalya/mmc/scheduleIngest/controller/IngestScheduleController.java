package com.apalya.mmc.scheduleIngest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apalya.mmc.scheduleIngest.service.SchedulePublishService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

@RestController

public class IngestScheduleController {
	
	@Autowired
	SchedulePublishService schedulePublishService;
	
	private final Logger log = LoggerFactory.getLogger(IngestScheduleController.class);
	@GetMapping("/version")
	public String checkVersion (){
		//model.addAttribute("appVersion","SIMS 1.0");
		System.out.println("Vachindi");
		return "SIMS 1.0";
	}
	
	
	@GetMapping ("/scheduleAndPublish")
	public void checkAndPublish(){
		log.info("In Schedule and Publish...");
		schedulePublishService.findSchedule();		
		log.info("Scheduled publish process end...");
		
	}
	
	
	
	

}
