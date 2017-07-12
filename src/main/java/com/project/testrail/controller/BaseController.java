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
}
