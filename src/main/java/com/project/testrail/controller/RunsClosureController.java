package com.project.testrail.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.testrail.ProjectNames;
import com.project.testrail.TestRuns;
import com.project.testrail.core.APIClient;

@Controller
public class RunsClosureController extends BaseController {

	private int projectId = 0;
	private String projectName = "";
	private int openTestRunsBefore = 0;
	private int openTestRunsAfter = 0;
	private String message = "";

	@RequestMapping("/runsclosure")
	public String index(Model model) {

		model.addAttribute("projectId", projectId);
		model.addAttribute("projectName", projectName);
		model.addAttribute("message", message);
		model.addAttribute("openTestRunsBefore", openTestRunsBefore);
		model.addAttribute("openTestRunsAfter", openTestRunsAfter);

		return "runsclosure";
	}

	@RequestMapping(value = "/closeRuns", method = RequestMethod.POST)
	public String closeRuns(int project_id) {
		APIClient client = getClient();
		setClient(client);
		
		projectId = project_id;

		ProjectNames projectNames = new ProjectNames();
		HashMap<Integer, String> projectList = projectNames.getList(client);
		if (projectList.containsKey(projectId)) {
			projectName = projectNames.getName(client, projectId);

			TestRuns testRuns = new TestRuns();

			// Get List of run ids for a given projectId
			ArrayList<Integer> runsList = testRuns.getList(client, projectId);
			// Get count of open test runs
			openTestRunsBefore = runsList.size();

			// Cycle through each runId within with arrayList and close it
			for (int runId : runsList) {
				testRuns.closeRun(client, runId);
			}
			// Get count of closed test runs
			openTestRunsAfter = testRuns.getList(client, projectId).size();
			
			message = "Test runs closed	 for specificed project";
		} else {
			projectId = project_id;
			projectName = "Not available";
			message = "ERROR while closing test runs for specified project. Verify the project id specified is correct";
		}

		return "redirect:/runsclosure";

	}

}
