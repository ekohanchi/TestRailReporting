package com.project.testrail.controller;

import java.util.ArrayList;
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

@Controller
public class ProjectController extends BaseController {
	@RequestMapping("/")
	public String index(Model model) {
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
		HashMap<Integer, Float> autoPercentage;
		
		ProjectNames projectName = new ProjectNames();
		TestCases testcases = new TestCases();
		
		projectList = projectName.getList(client);
		casePerProject = testcases.getTotalTCCount(client, projectList);
		autoCasePerProject = testcases.getTotalAutoTCCount(client, projectList);
		autoPercentage = testcases.getPercentageAutoTC(projectList, casePerProject, autoCasePerProject);
		
		for (Map.Entry<Integer, String> entry : projectList.entrySet()) {
			project = new Project();
			project.setProjectId(entry.getKey());
			project.setProjectName(entry.getValue());
			project.setTotalTcs(casePerProject.get(entry.getKey()));
			project.setTotalAutoTcs(autoCasePerProject.get(entry.getKey()));
			project.setAutoPercentage(autoPercentage.get(entry.getKey()) + "%");
			tcCountInProjectList.add(project);
		}
		
		return tcCountInProjectList;
	}
	
	
}
