package com.project.testrail.db.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProjectMetrics {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Date createdDate;
	
	private int projectId;
	private String projectName;
	private int totalCases;
	private int totalAutomated;
	private int notAutomatable;
	private Float percentAutomated;
	
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
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(int totalCases) {
		this.totalCases = totalCases;
	}
	public int getTotalAutomated() {
		return totalAutomated;
	}
	public void setTotalAutomated(int totalAutomated) {
		this.totalAutomated = totalAutomated;
	}
	public int getNotAutomatable() {
		return notAutomatable;
	}
	public void setNotAutomatable(int notAutomatable) {
		this.notAutomatable = notAutomatable;
	}
	public Float getPercentAutomated() {
		return percentAutomated;
	}
	public void setPercentAutomated(Float percentAutomated) {
		this.percentAutomated = percentAutomated;
	}
}
