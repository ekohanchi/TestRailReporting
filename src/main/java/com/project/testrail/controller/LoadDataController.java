package com.project.testrail.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.testrail.ProjectNames;
import com.project.testrail.TestCases;
import com.project.testrail.core.APIClient;
import com.project.testrail.db.model.LoadedAudit;
import com.project.testrail.db.model.LoadedAuditRepository;
import com.project.testrail.db.model.ProjectMetrics;
import com.project.testrail.db.model.ProjectMetricsRepository;
import com.project.testrail.model.TestCasesAttibutes;

@Controller
public class LoadDataController extends BaseController {
	@Autowired ProjectMetricsRepository projectMetricsRepository;
	@Autowired LoadedAuditRepository loadedAuditRepository;
	
	@RequestMapping(value = "/loaddata")
	public String index(Model model) {
				
		Date currentDate = getCurrentDate();
		
		// Store the current date in the loadedAudit table
		loadedAuditRepository.save(getLoadedAudit(currentDate));
		
		// Store all of the project metrics for the current date in the projectMetrics table
		List<ProjectMetrics> projectMetricsList = getProjectMetrics(currentDate);
		for(ProjectMetrics projectMetric : projectMetricsList) {
			projectMetricsRepository.save(projectMetric);
		}
		
		model.addAttribute("message", "Data has been loaded for: " + currentDate);
		return "loaddata";
	}
	
	private Date getCurrentDate() {
		return new Date();
	}
	
	private List<ProjectMetrics> getProjectMetrics(Date createdDate) {
		APIClient client = getClient();
		setClient(client);

		List<ProjectMetrics> projectMetricsList = new ArrayList<ProjectMetrics>();
		ProjectMetrics projectMetrics;

		HashMap<Integer, String> projectList;
		HashMap<Integer, Integer> casePerProject;
		HashMap<Integer, Integer> autoCasePerProject;
		HashMap<Integer, Integer> notAutomatablePerProject;
		HashMap<Integer, String> autoPercentage;

		HashMap<Integer, ArrayList<TestCasesAttibutes>> projectsAndTestCasesMap = new HashMap<Integer, ArrayList<TestCasesAttibutes>>();

		ProjectNames projectName = new ProjectNames();
		TestCases testcases = new TestCases(client);

		projectList = projectName.getList(client);

		projectsAndTestCasesMap = testcases.getFullMapOfProjectAndTCCount(projectList);
		casePerProject = getTCCountMapFromProjectMap(projectsAndTestCasesMap, 1);
		autoCasePerProject = getTCCountMapFromProjectMap(projectsAndTestCasesMap, 2);
		notAutomatablePerProject = getTCCountMapFromProjectMap(projectsAndTestCasesMap, 4);
		autoPercentage = testcases.getPercentageAutoTC(projectList, casePerProject, autoCasePerProject,
				notAutomatablePerProject);
		
		int projectId;
		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			projectId = entry.getKey();
			projectMetrics = new ProjectMetrics();
			projectMetrics.setCreatedDate(createdDate);
			projectMetrics.setProjectId(projectId);
			projectMetrics.setProjectName(entry.getValue());
			projectMetrics.setTotalCases(casePerProject.get(projectId));
			projectMetrics.setTotalAutomated(autoCasePerProject.get(projectId));
			projectMetrics.setNotAutomatable(notAutomatablePerProject.get(projectId));
			projectMetrics.setPercentAutomated(Float.parseFloat(autoPercentage.get(projectId)));
			projectMetricsList.add(projectMetrics);
		}

		return projectMetricsList;
	}
	
	/**
	 * This method will return back a map of project ids and equivalent test case
	 * count, either total, automated or not automated countType=1 : total TC count
	 * countType=2 : total auto TC count countType=3 : total not auto TC count
	 * countType=4 : total non auto TC count
	 * 
	 * @param projectsAndTestCasesMap
	 * @param countType
	 * @return
	 */
	private HashMap<Integer, Integer> getTCCountMapFromProjectMap(
			HashMap<Integer, ArrayList<TestCasesAttibutes>> projectsAndTestCasesMap, int countType) {

		HashMap<Integer, Integer> tcCountMap = new HashMap<Integer, Integer>();
		int i = 0;
		for (Map.Entry<Integer, ArrayList<TestCasesAttibutes>> entry : projectsAndTestCasesMap.entrySet()) {
			if (countType == 1) {
				tcCountMap.put(entry.getKey(), entry.getValue().get(i++).getTotalTCCount());
			} else if (countType == 2) {
				tcCountMap.put(entry.getKey(), entry.getValue().get(i++).getTotalAutoTCCount());
			} else if (countType == 3) {
				tcCountMap.put(entry.getKey(), entry.getValue().get(i++).getTotalNotAutoTCCount());
			} else if (countType == 4) {
				tcCountMap.put(entry.getKey(), entry.getValue().get(i++).getTotalNonAutoTCCount());
			}
		}

		return tcCountMap;
	}
	
	private LoadedAudit getLoadedAudit(Date createdDate) {
		LoadedAudit loadedAudit = new LoadedAudit();
		loadedAudit.setCreatedDate(createdDate);
		loadedAudit.setCreatedDateDisplay(createdDate.toString());
		return loadedAudit;
		
	}
	
}
