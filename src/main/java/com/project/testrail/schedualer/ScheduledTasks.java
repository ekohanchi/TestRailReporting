package com.project.testrail.schedualer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.project.testrail.controller.BaseController;

@Component
public class ScheduledTasks extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Scheduled(cron = "0 0 */3 * * *")
	public void loadDataToDB() {
		RestTemplate restTemplate = new RestTemplate();
		log.info("Making a call to the " + appConfig.getLoadDataUrl() + " endpoint to load data....");
		restTemplate.execute(appConfig.getLoadDataUrl(), HttpMethod.GET , null, null);
	}

}
