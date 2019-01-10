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
import com.project.testrail.controller.model.RunDetails;
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
		List<String> testsRemainingList = new ArrayList<>();
		int totalPassed = 0, totalBlocked = 0, totalUntested = 0, totalRetest = 0, totalFailed = 0,
				totalCustomStatus1 = 0, totalCustomStatus2 = 0, totalCustomStatus3 = 0, totalCustomStatus4 = 0,
				totalCustomStatus5 = 0, totalCustomStatus6 = 0, totalCustomStatus7 = 0;

		for (int i = 0; i < runIds.size(); i++) {
			int testRunTotal = 0;
			runId = Integer.parseInt(runIds.get(i));
			testRuns = new TestRuns();
			run = new Run();
			run = testRuns.getRun(client, runId);

			namesList.add(run.getName());
			projectIdList.add(run.getProjectId());
			is_completedList.add(run.getIs_completed());
			// get list of runIds from the source, parameter value
			totalPassed += run.getPassed_count();
			totalBlocked += run.getBlocked_count();
			totalUntested += run.getUntested_count();
			totalRetest += run.getRetest_count();
			totalFailed += run.getFailed_count();
			totalCustomStatus1 += run.getCustom_status1();
			totalCustomStatus2 += run.getCustom_status2();
			totalCustomStatus3 += run.getCustom_status3();
			totalCustomStatus4 += run.getCustom_status4();
			totalCustomStatus5 += run.getCustom_status5();
			totalCustomStatus6 += run.getCustom_status6();
			totalCustomStatus7 += run.getCustom_status7();

			testRunTotal = run.getPassed_count() + run.getBlocked_count() + run.getUntested_count()
					+ run.getRetest_count() + run.getFailed_count() + run.getCustom_status1() + run.getCustom_status2()
					+ run.getCustom_status3() + run.getCustom_status4() + run.getCustom_status5()
					+ run.getCustom_status6() + run.getCustom_status7();
			testsRemainingList.add(run.getUntested_count() + "/" + testRunTotal);
		}

		model.addAttribute("is_completed", getLogicalAndFromList(is_completedList));
		model.addAttribute("testrun_details", getRunDetails(client, projectIdList, runIds, testsRemainingList, namesList));
		model.addAttribute("passed_count", totalPassed);
		model.addAttribute("blocked_count", totalBlocked);
		model.addAttribute("untested_count", totalUntested);
		model.addAttribute("retest_count", totalRetest);
		model.addAttribute("failed_count", totalFailed);

		model.addAttribute("customStatus1_text", getCustomStatusText(1));
		model.addAttribute("customStatus2_text", getCustomStatusText(2));
		model.addAttribute("customStatus3_text", getCustomStatusText(3));
		model.addAttribute("customStatus4_text", getCustomStatusText(4));
		model.addAttribute("customStatus5_text", getCustomStatusText(5));
		model.addAttribute("customStatus6_text", getCustomStatusText(6));
		model.addAttribute("customStatus7_text", getCustomStatusText(7));

		model.addAttribute("customStatus1_count", totalCustomStatus1);
		model.addAttribute("customStatus2_count", totalCustomStatus2);
		model.addAttribute("customStatus3_count", totalCustomStatus3);
		model.addAttribute("customStatus4_count", totalCustomStatus4);
		model.addAttribute("customStatus5_count", totalCustomStatus5);
		model.addAttribute("customStatus6_count", totalCustomStatus6);
		model.addAttribute("customStatus7_count", totalCustomStatus7);

		return "runallstatusreport";
	}

	private boolean getLogicalAndFromList(List<Boolean> boolList) {
		if (boolList.contains(false)) {
			return false;
		} else {
			return true;
		}
	}

	private String getTestRunURL(String runId, String runName) {
		String runNameUrl = "<a href=\"" + appConfig.getBaseUrl() + "/index.php?/runs/view/" + runId
				+ "\" target=\"_blank\">" + runName + "</a>";
		return runNameUrl;
	}

	private List<RunDetails> getRunDetails(APIClient client, List<Integer> projectIdList, List<String> runIdList,
			List<String> testsRemainingList, List<String> runNamesList) {
		int i = 0;
		List<RunDetails> runDetailsList = new ArrayList<RunDetails>();
		RunDetails runDetails;
		ProjectNames pn = new ProjectNames();
		for (int projectId : projectIdList) {
			runDetails = new RunDetails();
			runDetails.setProjectName(pn.getName(client, projectId));
			runDetails.setRunId(runIdList.get(i));
			runDetails.setTestsRemaining(testsRemainingList.get(i));
			runDetails.setRunName(getTestRunURL(runIdList.get(i), runNamesList.get(i)));
			runDetailsList.add(runDetails);
			i++;
		}
		return runDetailsList;
	}
}
