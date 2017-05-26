package com.project.testrail;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.project.testrail.core.APIClient;

public class ProjectNames {
	public HashMap<Integer, String> getList(APIClient client) {
		Object projectObject = client.sendGet("get_projects");
		JsonElement projectJE = Utils.convertObjectToJson(projectObject);
		JsonArray projectJA = projectJE.getAsJsonArray();
		
		HashMap<Integer, String> projectList = new HashMap<Integer, String>();
		
		String projectName;
		int projectId;
		
		// Store the project name and project id into a map
		for (int i=0; i<projectJA.size(); i++) {
			projectName = projectJA.get(i).getAsJsonObject().get("name").getAsString();
			projectId = projectJA.get(i).getAsJsonObject().get("id").getAsInt();
			
			projectList.put(projectId, projectName);
		}
		
		// Print to console the values within the Map
//		Utils.printProjectList(projectList);
		
		return projectList;
	}
}
