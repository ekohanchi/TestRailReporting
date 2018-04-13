package com.project.testrail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.testrail.TestRuns;
import com.project.testrail.controller.model.Run;
import com.project.testrail.core.APIClient;

@Controller
@RequestMapping("/")
public class RunStatusReportController extends BaseController {
	
	private int runId = 0;
	
	@RequestMapping(value = "/runstatusreport")
	public String index(Model model, @RequestParam("testRunId") String run_id) {
		APIClient client = getClient();
		setClient(client);
		
		runId = Integer.parseInt(run_id);
		TestRuns testRuns = new TestRuns();
		Run run = new Run();
		run = testRuns.getRun(client, runId);
		
		model.addAttribute("name", run.getName());
		model.addAttribute("is_completed", run.getIs_completed());
		model.addAttribute("testRunId", String.valueOf(run.getRunId()));
		model.addAttribute("passed_count", run.getPassed_count());
		model.addAttribute("blocked_count", run.getBlocked_count());
		model.addAttribute("untested_count", run.getUntested_count());
		model.addAttribute("retest_count", run.getRetest_count());
		model.addAttribute("failed_count", run.getFailed_count());
		model.addAttribute("custom_status1", run.getCustom_status1());
		model.addAttribute("custom_status2", run.getCustom_status2());
		model.addAttribute("custom_status3", run.getCustom_status3());
		model.addAttribute("custom_status4", run.getCustom_status4());
		model.addAttribute("custom_status5", run.getCustom_status5());
		model.addAttribute("custom_status6", run.getCustom_status6());
		model.addAttribute("custom_status7", run.getCustom_status7());
		
		return "runstatusreport";
	}
}
