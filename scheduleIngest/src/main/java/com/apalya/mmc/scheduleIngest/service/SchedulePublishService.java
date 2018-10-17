package com.apalya.mmc.scheduleIngest.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apalya.mmc.scheduleIngest.config.ScheduleConfig;
import com.apalya.mmc.scheduleIngest.model.PublishStatus;
import com.apalya.mmc.scheduleIngest.repository.PublishStatusRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SchedulePublishService {

	@Autowired
	PublishStatusRepository publishStatusRepository;
	
	private final Logger log = LoggerFactory.getLogger(SchedulePublishService.class);

//Find schedules pending to be executed.
//Past scheduled tasks are published immediately and future are scheduled using ThreadPoolTaskScheduler
	public void findSchedule() {
		log.info("Finding schedules...");
		LocalDateTime currentDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("Asia/Kolkata"));
		log.info("Checking between dates",currentDateTime,currentDateTime.plusHours(1));
		//List<PublishStatus> publishList = publishStatusRepository.findByPublishStatusAndPublishDateBetween("scheduled", currentDateTime,currentDateTime.plusHours(1));
		List<PublishStatus> publishList = publishStatusRepository.findByPublishStatusAndPublishDateLessThanEqual("scheduled", currentDateTime);
		log.info("In Schedule service...{}",publishList.size());
		Iterator<PublishStatus> it = publishList.iterator();
		while (it.hasNext()){
			//log.info(it.next().toString());
			PublishStatus ps = it.next();
			threadPoolTaskScheduler(ps.getUserId() , ps.getStatusNames() , ps.getFkContentId(),ps.getPublishDate());
		}
		
	}
	
//schedule task on exact time
    public void  threadPoolTaskScheduler(String userId , String publishType , Long contentId, LocalDateTime publishSchedule){
    		Date utilDate = java.util.Date.from(publishSchedule.atZone(ZoneId.of("Asia/Kolkata")).toInstant());
    		log.info("convertedDate is:{}",utilDate);
    		ScheduleConfig.threadPoolTaskScheduler().schedule(new ScheduleTask(userId,publishType,contentId,utilDate),utilDate );
    		}



}
