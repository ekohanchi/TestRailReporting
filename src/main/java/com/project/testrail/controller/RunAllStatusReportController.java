package com.project.testrail.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.testrail.ProjectNames;
import com.project.testrail.TestRuns;
import com.project.testrail.controller.model.Run;
import com.project.testrail.core.APIClient;

@Controller
@RequestMapping("/")
public class RunAllStatusReportController extends BaseController {
	
	private int runId = 0;
	
	@RequestMapping(value = "/runallstatusreport")
	public String index(Model model, @RequestParam("testRunIds") String run_ids) {
		APIClient client = getClient();
		setClient(client);
		
		List<String> runIds = Arrays.asList(run_ids.split(","));
		TestRuns testRuns;
		Run run;
		List<String> namesList = new ArrayList<>();
		List<Boolean> is_completedList = new ArrayList<>();
		List<Integer> projectIdList = new ArrayList<>();
		int totalPassed=0,
				totalBlocked=0,
				totalUntested=0,
				totalRetest=0,
				totalFailed=0,
				totalCustomStatus1=0,
				totalCustomStatus2=0,
				totalCustomStatus3=0,
				totalCustomStatus4=0,
				totalCustomStatus5=0,
				totalCustomStatus6=0,
				totalCustomStatus7=0;

		
		for(int i=0; i<runIds.size(); i++) {
			runId = Integer.parseInt(runIds.get(i));
			testRuns = new TestRuns();
			run = new Run();
			run = testRuns.getRun(client, runId);
			
			namesList.add(run.getName());
			projectIdList.add(run.getProjectId());
			is_completedList.add(run.getIs_completed());
			// get list of runIds from the source, parameter value
			totalPassed+=run.getPassed_count();
			totalUntested+=run.getUntested_count();
			totalRetest+=run.getRetest_count();
			totalFailed+=run.getFailed_count();
			totalCustomStatus1+=run.getCustom_status1();
			totalCustomStatus2+=run.getCustom_status2();
			totalCustomStatus3+=run.getCustom_status3();
			totalCustomStatus4+=run.getCustom_status4();
			totalCustomStatus5+=run.getCustom_status5();
			totalCustomStatus6+=run.getCustom_status6();
			totalCustomStatus7+=run.getCustom_status7();
		}
				
		//model.addAttribute("name", namesList.toString());
		model.addAttribute("testrun_names", convertListToHTMLNewLine(namesList));
		model.addAttribute("project_names", getProjectNamesFromIds(client, projectIdList) );
		model.addAttribute("is_completed", getLogicalAndFromList(is_completedList));
		model.addAttribute("testRunIds", getValuesFromList(runIds));
		model.addAttribute("passed_count", totalPassed);
		model.addAttribute("blocked_count", totalBlocked);
		model.addAttribute("untested_count", totalUntested);
		model.addAttribute("retest_count", totalRetest);
		model.addAttribute("failed_count", totalFailed);
		model.addAttribute("custom_status1", totalCustomStatus1);
		model.addAttribute("custom_status2", totalCustomStatus2);
		model.addAttribute("custom_status3", totalCustomStatus3);
		model.addAttribute("custom_status4", totalCustomStatus4);
		model.addAttribute("custom_status5", totalCustomStatus5);
		model.addAttribute("custom_status6", totalCustomStatus6);
		model.addAttribute("custom_status7", totalCustomStatus7);
		
		return "runallstatusreport";
	}
	
	private boolean getLogicalAndFromList(List<Boolean> boolList) {
		if(boolList.contains(false)) {
			return false;
		} else {
			return true;
		}
	}
	
	private String convertListToHTMLNewLine(List<String> namesList) {
		String itemPerLineString = "";
		for (String name : namesList) {
			itemPerLineString += (name + "<br>");
		}
		return itemPerLineString;
	}
	
	private String getProjectNamesFromIds(APIClient client, List<Integer> projectIds) {
		String projectNamesPerLine = "";
		ProjectNames projectNames = new ProjectNames();
		for (int projectId : projectIds) {
			projectNamesPerLine += (projectNames.getName(client, projectId) + "<br>");
		}
		return projectNamesPerLine;
	}
	
	private String getValuesFromList (List<String> runIds) {
		String idsList = "";
		for (String runId : runIds) {
			idsList += (runId + ", ");
		}
		// Return string without the last ','
		return idsList.substring(0, idsList.length() - 2);
	}
}
