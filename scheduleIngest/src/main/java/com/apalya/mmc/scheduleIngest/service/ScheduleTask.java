package com.apalya.mmc.scheduleIngest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.apalya.mmc.scheduleIngest.utilities.RestUtililty;

public class ScheduleTask implements Runnable {
		
		Logger log = LoggerFactory.getLogger(ScheduleTask.class);
	    private String userId;
	    private String publishType;
	    private Long contentId;
	    private Date scheduledDate;
	    
	    public ScheduleTask(String userId , String publishType , Long contentId,Date scheduleDate){
	    	this.userId = userId;
	    	this.contentId=contentId;
	    	this.publishType = publishType;
	    	this.scheduledDate = scheduleDate;
	    }
	     
	    @Override
	    public void run() {
	        runSchedule();
	    }
	    
	    public void runSchedule(){
	    	log.info("Scheduled publish {} at {} into {} by user {}",contentId,scheduledDate,publishType,userId);
	        List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("contentId", contentId.toString()));
	        params.add(new BasicNameValuePair("userId", userId));
	        params.add(new BasicNameValuePair("publishingType", publishType));
	        try {
	        	log.info("sending Post...");
				RestUtililty.sendPost("http://stagv2cms.sunnxt.in:8080/api/publish/publishContent", params);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}

