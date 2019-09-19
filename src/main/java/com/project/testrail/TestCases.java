package com.project.testrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.testrail.core.APIClient;

public class TestCases {
	public HashMap<Integer, Integer> getTotalTCCount(APIClient client, HashMap<Integer, String> projectList) {
		int projectId, tcCount = 0;
		HashMap<Integer, Integer> casesPerProject = new HashMap<Integer, Integer>();

		// Store the project id and count of test cases within the project into
		// a map
		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			if (getProjectIgnoreList().contains(projectId)) {
				tcCount = 0;
			} else {
				if (getSuiteMode(client, projectId) == 1) {
					Object object = client.sendGet("get_cases/" + projectId);
					JsonElement casesJE = Utils.convertObjectToJson(object);
					JsonArray casesJA = casesJE.getAsJsonArray();
					tcCount = casesJA.size();
				} else {
					// if suiteMode is either 2 or 3
					for (int suiteID : getSuiteIds(client, projectId)) {
						Object object = client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteID);
						JsonElement casesJE = Utils.convertObjectToJson(object);
						JsonArray casesJA = casesJE.getAsJsonArray();
						tcCount = casesJA.size();
					}

				}
			}

			casesPerProject.put(projectId, tcCount);
		}

		// Print to console the values within the Map
		// Utils.printCasesPerProject(casesPerProject);

		return casesPerProject;
	}

	private int getSuiteMode(APIClient client, int projectId) {
		Object object = client.sendGet("get_project/" + projectId);
		JsonElement projectJE = Utils.convertObjectToJson(object);
		JsonObject projectJO = projectJE.getAsJsonObject();
		int suiteMode = projectJO.get("suite_mode").getAsInt();
		return suiteMode;
	}

	private ArrayList<Integer> getSuiteIds(APIClient client, int projectId) {
		Object object = client.sendGet("get_suites/" + projectId);
		JsonElement suiteJE = Utils.convertObjectToJson(object);
		JsonArray suiteJA = suiteJE.getAsJsonArray();
		JsonObject suiteJO = new JsonObject();
		ArrayList<Integer> suiteIDs = new ArrayList<Integer>();
		for (int i = 0; i < suiteJA.size(); i++) {
			suiteJO = suiteJA.get(i).getAsJsonObject();
			suiteIDs.add(suiteJO.get("id").getAsInt());
		}
		return suiteIDs;
	}

	/**
	 * This method should be called if you want to get a count of test cases by
	 * 'type_id' value
	 * 
	 * @param client
	 * @param projectList - the list of projects
	 * @param typeId      - the type_id value from testrail
	 * @return
	 */
	public HashMap<Integer, Integer> getTCCountByID(APIClient client, HashMap<Integer, String> projectList,
			int typeId) {
		int projectId, tcCount;
		HashMap<Integer, Integer> casesPerProject = new HashMap<Integer, Integer>();

		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			if (getProjectIgnoreList().contains(projectId)) {
				tcCount = 0;
			} else {
				Object object = client.sendGet("get_cases/" + projectId + "&type_id=" + typeId);
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

	/**
	 * This method should be called if you want to get a count of test cases by
	 * custom drop down created called "Automation Status"
	 * 
	 * @param client
	 * @param projectList - the list of projects
	 * @param autoStatus  - the automation status value set for the test case 1-Not
	 *                    Automated 2-Automated 3-Not Automatable
	 * @return
	 */

	public HashMap<Integer, Integer> getTCCountByAutoStatus(APIClient client, HashMap<Integer, String> projectList,
			int autoStatus) {
		int projectId, tcCount;
		HashMap<Integer, Integer> casesPerProject = new HashMap<Integer, Integer>();

		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			tcCount = 0;
			if (getProjectIgnoreList().contains(projectId)) {
				tcCount = 0;
			} else {
				if (getSuiteMode(client, projectId) == 1) {

					Object object = client.sendGet("get_cases/" + projectId);
					JsonElement casesJE = Utils.convertObjectToJson(object);
					JsonArray casesJA = casesJE.getAsJsonArray();
					JsonObject caseJO;
					JsonElement customAutoStatusJE;
					for (int i = 0; i < casesJA.size(); i++) {
						caseJO = casesJA.get(i).getAsJsonObject();
						customAutoStatusJE = caseJO.get("custom_automation_status");
						if (customAutoStatusJE != null) {
							if (customAutoStatusJE.getAsInt() == autoStatus) {
								tcCount++;
							}
						}

					}
				} else {
					// if suiteMode is either 2 or 3
					for (int suiteID : getSuiteIds(client, projectId)) {
						Object object = client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteID);
						JsonElement casesJE = Utils.convertObjectToJson(object);
						JsonArray casesJA = casesJE.getAsJsonArray();
						JsonObject caseJO;
						JsonElement customAutoStatusJE;
						for (int i = 0; i < casesJA.size(); i++) {
							caseJO = casesJA.get(i).getAsJsonObject();
							customAutoStatusJE = caseJO.get("custom_automation_status");
							if (customAutoStatusJE != null) {
								if (customAutoStatusJE.getAsInt() == autoStatus) {
									tcCount++;
								}
							}

						}
					}
				}
			}
			casesPerProject.put(projectId, tcCount);
		}

		return casesPerProject;
	}

	private ArrayList<Integer> getProjectIgnoreList() {
		ArrayList<Integer> ignoreList = new ArrayList<Integer>();

		return ignoreList;
	}

	public HashMap<Integer, String> getPercentageAutoTC(HashMap<Integer, String> projectList,
			HashMap<Integer, Integer> totalTC, HashMap<Integer, Integer> totalAutoTC,
			HashMap<Integer, Integer> notAutomatableTC) {

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
					percentage = ((double) totalAutoTC.get(projectId)
							/ ((double) totalTC.get(projectId) - (double) notAutomatableTC.get(projectId))) * 100;
					// percentage = (double) ((totalAutoTC.get(projectId) * 100) /
					// totalTC.get(projectId));
				}
			}
			percentageAutoTC.put(projectId, String.format("%.2f", percentage));
		}

		return percentageAutoTC;
	}
}
