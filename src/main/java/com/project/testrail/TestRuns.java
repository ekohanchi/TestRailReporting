package com.project.testrail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.testrail.controller.model.Run;
import com.project.testrail.core.APIClient;
import com.project.testrail.core.APIException;

public class TestRuns {
	private final static Logger log = LoggerFactory.getLogger(Utils.class.getName());

	public ArrayList<Integer> getList(APIClient client, int projectId) {
		Object runsObject = client.sendGet("get_runs/" + projectId + "&is_completed=0");
		JsonElement runsJE = Utils.convertObjectToJson(runsObject);
		JsonArray runsJA = runsJE.getAsJsonArray();

		ArrayList<Integer> runsList = new ArrayList<Integer>();
		int runId;
		for (int i = 0; i < runsJA.size(); i++) {
			runId = runsJA.get(i).getAsJsonObject().get("id").getAsInt();
			runsList.add(runId);
		}

		log.info("List of ids within the runsList: " + runsList.toString());
		return runsList;

	}

	public void closeRun(APIClient client, int runId) {
		try {
			log.info("Closing run with runId: " + runId + " ...");
			client.sendPost("close_run/" + runId, "");
		} catch (IOException | APIException ex) {
			log.error(ex.getMessage());
		}
	}
	
	public Run getRun(APIClient client, int runId) {
			log.info("Getting results for test run with runId: " + runId + " ...");
			Object runObject = client.sendGet("get_run/" + runId);
			JsonElement runJE = Utils.convertObjectToJson(runObject);
			JsonObject runJO = runJE.getAsJsonObject();
			
			Run run = new Run();
			run.setName(runJO.get("name").getAsString());
			run.setIs_completed(runJO.get("is_completed").getAsBoolean());
			run.setRunId(runId);
			run.setPassed_count(runJO.get("passed_count").getAsInt());
			run.setBlocked_count(runJO.get("blocked_count").getAsInt());
			run.setUntested_count(runJO.get("untested_count").getAsInt());
			run.setRetest_count(runJO.get("retest_count").getAsInt());
			run.setFailed_count(runJO.get("failed_count").getAsInt());
			run.setCustom_status1(runJO.get("custom_status1_count").getAsInt());
			run.setCustom_status2(runJO.get("custom_status2_count").getAsInt());
			run.setCustom_status3(runJO.get("custom_status3_count").getAsInt());
			run.setCustom_status4(runJO.get("custom_status4_count").getAsInt());
			run.setCustom_status5(runJO.get("custom_status5_count").getAsInt());
			run.setCustom_status6(runJO.get("custom_status6_count").getAsInt());
			run.setCustom_status7(runJO.get("custom_status7_count").getAsInt());
			
			return run;
	}
	
	public ArrayList<Integer> getResultsForRun(APIClient client, int runId) {
		log.info("Getting detailed results for test run with runId: " + runId + " ...");
		Object runObject = client.sendGet("get_results_for_run/" + runId);
		JsonElement runJE = Utils.convertObjectToJson(runObject);
		JsonArray runsJA = runJE.getAsJsonArray();
		
		ArrayList<Integer> testIdList = new ArrayList<Integer>();
		int testId;
		for (int i=0; i<runsJA.size(); i++) {
			testId = runsJA.get(i).getAsJsonObject().get("test_id").getAsInt();
			testIdList.add(testId);
		}
		
		// Making the list a unique list
		ArrayList<Integer> uniqueTestIdList =  new ArrayList<Integer> (testIdList.stream().distinct().collect(Collectors.toList()));
		//log.info("List of test case ids within runid: " + runId + " - " + testIdList.toString());
		log.info("List of unique test case ids within runid: " + runId + " - " + uniqueTestIdList.toString());
		return uniqueTestIdList;
		
	}
	
	public ArrayList<Integer> getTestResultsForCase(APIClient client, ArrayList<Integer> testIdList) {
		Object testObject;
		JsonElement testJE;
		JsonObject testJO;
		
		ArrayList<Integer> testTypeList = new ArrayList<Integer>();
		for(int i=0; i<testIdList.size(); i++) {
			testObject = client.sendGet("get_test/" + testIdList.get(i));
			testJE = Utils.convertObjectToJson(testObject);
			testJO = testJE.getAsJsonObject();
			testTypeList.add(testJO.get("type_id").getAsInt());
		}
		return testTypeList;
	}
	
	public int getTypeCountFromList(ArrayList<Integer> testTypeList, int typeId) {
		int counter=0;
		for(int i=0; i<testTypeList.size(); i++) {
			if(testTypeList.get(i).intValue() == typeId) {
				counter++;
			}
		}
		return counter;
	}
}
