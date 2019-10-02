package com.project.testrail.db.model;

public class ProjectDetails {
	private int projectId;
	private String projectName;
	private int totalCases;
	private int totalAutomated;
	private int notAutomatable;
	private String percentAutomated;
	
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
	public String getPercentAutomated() {
		return percentAutomated;
	}
	public void setPercentAutomated(String percentAutomated) {
		this.percentAutomated = percentAutomated;
	}
	
	

}
