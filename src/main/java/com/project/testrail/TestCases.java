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
		ignoreList.add(12); // Automation-Integration-POC

		return ignoreList;
	}

	public HashMap<Integer, String> getPercentageAutoTC(HashMap<Integer, String> projectList,
			HashMap<Integer, Integer> totalTC, HashMap<Integer, Integer> totalAutoTC) {

		int projectId;
		double percentage;
		HashMap<Integer, String> percentageAutoTC = new HashMap<Integer, String>();

		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			if (getProjectIgnoreList().contains(projectId)) {
				percentage = 0;
			} else {
				if (totalTC.get(projectId) == 0) {
					percentage = 0;
				} else {
					percentage = ((double) totalAutoTC.get(projectId) / (double) totalTC.get(projectId)) * 100;
					//percentage = (double) ((totalAutoTC.get(projectId) * 100) / totalTC.get(projectId));
				}
			}
			percentageAutoTC.put(projectId, String.format("%.2f", percentage));
		}

		return percentageAutoTC;
	}
}
