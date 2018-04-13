package com.project.testrail.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.testrail.TestRuns;
import com.project.testrail.controller.model.Run;
import com.project.testrail.core.APIClient;

@Controller
@RequestMapping("/")
public class RunTypeReportController extends BaseController {
	
	private int runId = 0;
	
	@RequestMapping(value = "/runtypereport")
	public String index(Model model, @RequestParam("testRunId") String run_id) {
		APIClient client = getClient();
		setClient(client);
		
		runId = Integer.parseInt(run_id);
		TestRuns testRuns = new TestRuns();
		Run run = new Run();
		run = testRuns.getRun(client, runId);
		
		ArrayList<Integer> testIdList = testRuns.getResultsForRun(client, runId);
		ArrayList<Integer> testTypeList = testRuns.getTestResultsForCase(client, testIdList);
		
		model.addAttribute("name", run.getName());
		model.addAttribute("testRunId", String.valueOf(run.getRunId()));
		model.addAttribute("acceptance", testRuns.getTypeCountFromList(testTypeList, 1));
		model.addAttribute("accessibility", testRuns.getTypeCountFromList(testTypeList, 2));
		model.addAttribute("automated", testRuns.getTypeCountFromList(testTypeList, 3));
		model.addAttribute("compatibility", testRuns.getTypeCountFromList(testTypeList, 4));
		model.addAttribute("destructive", testRuns.getTypeCountFromList(testTypeList, 5));
		model.addAttribute("functional", testRuns.getTypeCountFromList(testTypeList, 6));
		model.addAttribute("other", testRuns.getTypeCountFromList(testTypeList, 7));
		model.addAttribute("performance", testRuns.getTypeCountFromList(testTypeList, 8));
		model.addAttribute("regression", testRuns.getTypeCountFromList(testTypeList, 9));
		model.addAttribute("security", testRuns.getTypeCountFromList(testTypeList, 10));
		model.addAttribute("smoke_sanity", testRuns.getTypeCountFromList(testTypeList, 11));
		model.addAttribute("usability", testRuns.getTypeCountFromList(testTypeList, 12));
		model.addAttribute("not_automatable", testRuns.getTypeCountFromList(testTypeList, 13));
		
		return "runtypereport";
	}
}
