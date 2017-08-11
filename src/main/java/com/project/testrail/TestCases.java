package com.project.testrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.project.testrail.core.APIClient;

public class TestCases {
	public HashMap<Integer, Integer> getTotalTCCount(APIClient client, HashMap<Integer, String> projectList) {
		int projectId, tcCount;
		HashMap<Integer, Integer> casesPerProject = new HashMap<Integer, Integer>();

		// Store the project id and count of test cases within the project into
		// a map
		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			// TODO - find out why projectId 19 requires suite number to be
			// specified
			if (getProjectIgnoreList().contains(projectId)) {
				tcCount = 0;
			} else {
				Object object = client.sendGet("get_cases/" + projectId);
				JsonElement casesJE = Utils.convertObjectToJson(object);
				JsonArray casesJA = casesJE.getAsJsonArray();
				tcCount = casesJA.size();
			}

			casesPerProject.put(projectId, tcCount);
		}

		// Print to console the values within the Map
		// Utils.printCasesPerProject(casesPerProject);

		return casesPerProject;
	}

	public HashMap<Integer, Integer> getTotalAutoTCCount(APIClient client, HashMap<Integer, String> projectList) {
		int projectId, tcCount;
		HashMap<Integer, Integer> casesPerProject = new HashMap<Integer, Integer>();

		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			// TODO - find out why projectId 19 requires suite number to be
			// specified
			if (getProjectIgnoreList().contains(projectId)) {
				tcCount = 0;
			} else {
				Object object = client.sendGet("get_cases/" + projectId + "&type_id=3");
				JsonElement casesJE = Utils.convertObjectToJson(object);
				JsonArray casesJA = casesJE.getAsJsonArray();
				tcCount = casesJA.size();
			}

			casesPerProject.put(projectId, tcCount);
		}

		// Print to console the values within the Map
		// Utils.printCasesPerProject(casesPerProject);

		return casesPerProject;

	}

	private ArrayList<Integer> getProjectIgnoreList() {
		ArrayList<Integer> ignoreList = new ArrayList<Integer>();

		ignoreList.add(19); // Veritix Platform Regression Suite

		return ignoreList;
	}

	public HashMap<Integer, Float> getPercentageAutoTC(HashMap<Integer, String> projectList,
			HashMap<Integer, Integer> totalTC, HashMap<Integer, Integer> totalAutoTC) {

		int projectId;
		float percentage;
		HashMap<Integer, Float> percentageAutoTC = new HashMap<Integer, Float>();

		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			if (getProjectIgnoreList().contains(projectId)) {
				percentage = 0;
			} else {
				if (totalTC.get(projectId) == 0) {
					percentage = 0;
				} else {
					percentage = (float) ((totalAutoTC.get(projectId) * 100) / totalTC.get(projectId));
				}
			}
			percentageAutoTC.put(projectId, percentage);
		}

		return percentageAutoTC;
	}
}
