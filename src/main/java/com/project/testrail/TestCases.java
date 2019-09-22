package com.project.testrail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.testrail.core.APIClient;
import com.project.testrail.model.TestCasesAttibutes;

public class TestCases {
	private APIClient client;
	private int automatedStatus = 1;
	private int notAutomatedStatus = 2;
	//private int notAutomatableStatus = 3;
	
	private String automationSystemName = "custom_automation";

	public TestCases(APIClient apiClient) {
		this.client = apiClient;
	}

	public HashMap<Integer, ArrayList<TestCasesAttibutes>> getFullMapOfProjectAndTCCount(HashMap<Integer, String> projectList) {
		int projectId, tcCount = 0;
		ArrayList<Integer> autoTestStatusCountList = new ArrayList<Integer>();
		TestCasesAttibutes testCasesAttributes = new TestCasesAttibutes();
		HashMap<Integer, ArrayList<TestCasesAttibutes>> projectsAndTestCasesMap = new HashMap<Integer, ArrayList<TestCasesAttibutes>>();
		ArrayList<TestCasesAttibutes> testCasesAttributeList = new ArrayList<TestCasesAttibutes>();

		// Store the project id and count of test cases within the project into
		// a map
		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			autoTestStatusCountList.clear();
			testCasesAttributes = new TestCasesAttibutes();
			
			projectId = entry.getKey();
			if (getProjectIgnoreList().contains(projectId)) {
				tcCount = 0;
			} else {
				if (getSuiteMode(projectId) == 1) {
					Object object = client.sendGet("get_cases/" + projectId);
					JsonElement casesJE = Utils.convertObjectToJson(object);
					JsonArray casesJA = casesJE.getAsJsonArray();
					autoTestStatusCountList = getAutoStatusCount(casesJA);
					tcCount = casesJA.size();
				} else {
					// if suiteMode is either 2 or 3
					for (int suiteID : getSuiteIds(projectId)) {
						Object object = client.sendGet("get_cases/" + projectId + "&suite_id=" + suiteID);
						JsonElement casesJE = Utils.convertObjectToJson(object);
						JsonArray casesJA = casesJE.getAsJsonArray();
						autoTestStatusCountList = getAutoStatusCount(casesJA);
						tcCount = casesJA.size();
					}

				}
			}
			
			testCasesAttributes.setTotalTCCount(tcCount);
			testCasesAttributes.setTotalAutoTCCount(autoTestStatusCountList.get(0));
			testCasesAttributes.setTotalNotAutoTCCount(autoTestStatusCountList.get(1));
			testCasesAttributes.setTotalNonAutoTCCount(autoTestStatusCountList.get(2));
			
			testCasesAttributeList.add(testCasesAttributes);
			
			projectsAndTestCasesMap.put(projectId, testCasesAttributeList);			
		}
		return projectsAndTestCasesMap;
	}

	private int getSuiteMode(int projectId) {
		Object object = client.sendGet("get_project/" + projectId);
		JsonElement projectJE = Utils.convertObjectToJson(object);
		JsonObject projectJO = projectJE.getAsJsonObject();
		int suiteMode = projectJO.get("suite_mode").getAsInt();
		return suiteMode;
	}

	private ArrayList<Integer> getSuiteIds(int projectId) {
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
	 * This method will return back an ArrayList of integers containing:
	 * 
	 * 0: automatedTCCount
	 * 1: notAutomatedTCCount
	 * 2: notAutomatableTCCount
	 * 
	 * @param casesJA
	 * @return
	 */
	private ArrayList<Integer> getAutoStatusCount(JsonArray casesJA) {
		int automatedTCCount = 0;
		int notAutomatedTCCount = 0;
		int notAutomatableTCCount = 0;
		
		ArrayList<Integer> autoTestCountList = new ArrayList<Integer>();
		
		JsonObject caseJO;
		JsonElement customAutoJE;
		for (int i=0; i<casesJA.size(); i++) {
			caseJO = casesJA.get(i).getAsJsonObject();
			customAutoJE = caseJO.get(automationSystemName);
			if (customAutoJE != null) {
				if (customAutoJE.getAsInt() == automatedStatus) {
					automatedTCCount++;
				} else if (customAutoJE.getAsInt() == notAutomatedStatus) {
					notAutomatedTCCount++;
				} else {
					notAutomatableTCCount++;
				}
			}
		}
		autoTestCountList.add(automatedTCCount);
		autoTestCountList.add(notAutomatedTCCount);
		autoTestCountList.add(notAutomatableTCCount);
		return autoTestCountList;
	}

	/**
	 * List any project ids that need to be ignored for any reason
	 * @return
	 */
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
				}
			}
			percentageAutoTC.put(projectId, String.format("%.2f", percentage));
		}

		return percentageAutoTC;
	}
}
