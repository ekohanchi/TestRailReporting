package com.project.testrail.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.testrail.controller.model.Project;
import com.project.testrail.db.model.ProjectDetails;
import com.project.testrail.db.model.ProjectMetrics;
import com.project.testrail.db.model.ProjectMetricsRepository;

@Controller
public class ProjectController extends BaseController {
	@Autowired
	ProjectMetricsRepository repository;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("pageLogo", getPageLogoPath());
		model.addAttribute("todaysDate", getLatestDatePulled());
		model.addAttribute("projects", getProjectDetails());
		return "projects";
	}

	private String getLatestDatePulled() {
		ProjectMetrics projectMetrics = getMostRecentProjectMetricsFromDB();
		
		if (projectMetrics == null) {
			return "Never";
		} else {
			return projectMetrics.getCreatedDate().toString();
		}
	}
	
	private List<Project> getProjectDetails() {
		ProjectMetrics projectMetrics = getMostRecentProjectMetricsFromDB();
		List<Project> projectList = new ArrayList<Project>();

		if (projectMetrics == null) {
			// return an empty list if db isn't populated
			return projectList;
		} else {
			Project project;
			for (ProjectDetails projectDetails : projectMetrics.getProjectList()) {
				project = new Project();
				project.setProjectId(projectDetails.getProjectId());
				project.setProjectName(projectDetails.getProjectName());
				project.setTotalTcs(projectDetails.getTotalCases());
				project.setTotalAutoTcs(projectDetails.getTotalAutomated());
				project.setNotAutomatableTcs(projectDetails.getNotAutomatable());
				project.setAutoPercentage(projectDetails.getPercentAutomated());

				projectList.add(project);
			}
			return projectList;
		}
	}

	private ProjectMetrics getMostRecentProjectMetricsFromDB() {
		Sort sort = new Sort(Sort.Direction.DESC, ProjectMetricsRepository.createdDate);

		List<ProjectMetrics> projectMetricsListMostRecentSorted = repository.findAll(sort);

		if (projectMetricsListMostRecentSorted.size() == 0) {
			return null;
		} else {
			ProjectMetrics projectMetrics = projectMetricsListMostRecentSorted.get(0);
			return projectMetrics;
		}
	}
}
