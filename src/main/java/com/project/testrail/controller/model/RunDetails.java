package com.project.testrail.controller.model;

public class RunDetails {
	private String projectName;
	private String runId;
	private String testsRemaining;
	private String runName;	
	
	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRunId() {
		return runId;
	}
	
	public void setRunId(String runId) {
		this.runId = runId;
	}
	
	public String getTestsRemaining() {
		return testsRemaining;
	}

	public void setTestsRemaining(String testsRemaining) {
		this.testsRemaining = testsRemaining;
	}

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}
	
	
	
}
