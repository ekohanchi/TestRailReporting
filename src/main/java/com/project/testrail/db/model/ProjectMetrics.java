package com.project.testrail.db.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class ProjectMetrics {

	@Id
	public String id;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	public Date createdDate;
	
	public List<ProjectDetails> projectList;

	public ProjectMetrics() {

	}

	public ProjectMetrics(Date date, List<ProjectDetails> projectList) {
		this.createdDate = date;
		this.projectList = projectList;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public List<ProjectDetails> getProjectList() {
		return projectList;
	}
	
	

}
