package com.apalya.mmc.scheduleIngest.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apalya.mmc.scheduleIngest.model.PublishStatus;

@Repository
public interface PublishStatusRepository extends JpaRepository<PublishStatus,Integer> {

	@Transactional
	public List<PublishStatus> findByPublishStatus(String status);
	
	@Transactional
	public List<PublishStatus> findByPublishStatusAndPublishDateBetween(String status, LocalDateTime scheduledDate,LocalDateTime plusOneHour);
	
	@Transactional
	public List<PublishStatus> findByPublishStatusAndPublishDateLessThanEqual(String status, LocalDateTime scheduledDate);
	
}
