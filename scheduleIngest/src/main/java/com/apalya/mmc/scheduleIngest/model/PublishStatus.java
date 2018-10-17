package com.apalya.mmc.scheduleIngest.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name = "publish_status",catalog = "myplex_cms")
public class PublishStatus {

	@Id
	private Long id;
	private Long fkContentId;
	private String statusNames;
	private String publishStatus;
	private LocalDateTime publishDate;
	private String unpublishStatus;
	private LocalDateTime unpublishDate;
	private String userId;
	
}
