package com.project.testrail.db.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoadedAudit {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	//@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;
	
	private String createdDateDisplay;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedDateDisplay() {
		return createdDateDisplay;
	}
	public void setCreatedDateDisplay(String createdDateDisplay) {
		this.createdDateDisplay = createdDateDisplay;
	}
	
}
