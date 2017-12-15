package com.project.testrail.controller.model;

public class Project {
	private int projectId;
	private String projectName;
	private int totalTcs;
	private int totalAutoTcs;
	private int notAutomatableTcs;
	private String autoPercentage;
	
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
	public int getTotalTcs() {
		return totalTcs;
	}
	public void setTotalTcs(int totalTcs) {
		this.totalTcs = totalTcs;
	}
	public int getTotalAutoTcs() {
		return totalAutoTcs;
	}
	public void setTotalAutoTcs(int totalAutoTcs) {
		this.totalAutoTcs = totalAutoTcs;
	}
	public int getNotAutomatableTcs() {
		return notAutomatableTcs;
	}
	public void setNotAutomatableTcs(int notAutomatableTcs) {
		this.notAutomatableTcs = notAutomatableTcs;
	}
	public String getAutoPercentage() {
		return autoPercentage;
	}
	public void setAutoPercentage(String autoPercentage) {
		this.autoPercentage = autoPercentage;
	}
}
