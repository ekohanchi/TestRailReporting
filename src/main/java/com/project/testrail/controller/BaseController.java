package com.project.testrail.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.testrail.ApplicationConfig;
import com.project.testrail.core.APIClient;

public class BaseController {
	//private final static Logger log = LoggerFactory.getLogger(BaseController.class.getName());
	
	@Autowired
	protected ApplicationConfig appConfig;
	
	protected APIClient client;
	
//	client = getClient();
//	setClient(client);
	
	public APIClient getClient() {
		APIClient client = new APIClient(appConfig.getBaseUrl());
		client.setUser(appConfig.getUserName());
		client.setPassword(appConfig.getPassword());
		
		return client;
	}
	
	public void setClient(APIClient apiclient) {
		client = apiclient;
	}
	
	public String getPageLogoPath() {
		return appConfig.getPageLogoPath();
	}
	
	public String getCustomStatusText(int statusId) {
		String customStatusText = "";
		if (statusId == 1) {
			customStatusText = appConfig.getCustomStatus1Text();
		} else if (statusId == 2) {
			customStatusText = appConfig.getCustomStatus2Text();
		} else if (statusId == 3) {
			customStatusText = appConfig.getCustomStatus3Text();
		} else if (statusId == 4) {
			customStatusText = appConfig.getCustomStatus4Text();
		} else if (statusId == 5) {
			customStatusText = appConfig.getCustomStatus5Text();
		} else if (statusId == 6) {
			customStatusText = appConfig.getCustomStatus6Text();
		} else if (statusId == 7) {
			customStatusText = appConfig.getCustomStatus7Text();
		}
		
		return customStatusText;
	}
}
