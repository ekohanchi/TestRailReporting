package com.project.testrail;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Utils {
	private final static Logger log = LoggerFactory.getLogger(Utils.class.getName());
	
	public static JsonElement convertObjectToJson(Object object) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(object);
		return jsonElement;
	}
	
	public static void printProjectList(HashMap<Integer, String> projectList) {
		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			
			log.info(entry.getKey() + " " + entry.getValue());
		}
	}
	
	public static void printCasesPerProject(HashMap<Integer, Integer> casesPerProject) {
		for (Map.Entry<Integer, Integer> entry : casesPerProject.entrySet()) {
			log.info(entry.getKey() + "-" + entry.getValue());
		}
	}
	
	public static void printApiCall(String method, URL url) {
		log.info("Sending request: " + method + " - " + url);
	}
}
