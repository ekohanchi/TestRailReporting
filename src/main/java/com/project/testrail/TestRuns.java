package com.project.testrail;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
}
