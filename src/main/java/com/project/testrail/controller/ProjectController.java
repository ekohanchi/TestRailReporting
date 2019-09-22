package com.project.testrail.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.testrail.ProjectNames;
import com.project.testrail.TestCases;
import com.project.testrail.controller.model.Project;
import com.project.testrail.core.APIClient;
import com.project.testrail.model.TestCasesAttibutes;

@Controller
public class ProjectController extends BaseController {
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("pageLogo", getPageLogoPath());
		model.addAttribute("todaysDate", populateCurrentDate());
		model.addAttribute("projects", populateProjectList());
		return "projects";
	}

	private List<Project> populateProjectList() {
		APIClient client = getClient();
		setClient(client);

		List<Project> tcCountInProjectList = new ArrayList<Project>();
		Project project; // = new Projects();

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

		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			project = new Project();
			project.setProjectId(entry.getKey());
			project.setProjectName(entry.getValue());
			project.setTotalTcs(casePerProject.get(entry.getKey()));
			project.setTotalAutoTcs(autoCasePerProject.get(entry.getKey()));
			project.setNotAutomatableTcs(notAutomatablePerProject.get(entry.getKey()));
			project.setAutoPercentage(autoPercentage.get(entry.getKey()) + "%");
			tcCountInProjectList.add(project);
		}

		return tcCountInProjectList;
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

	private String populateCurrentDate() {
		Date today = Calendar.getInstance().getTime();
		return today.toString();
	}

}
