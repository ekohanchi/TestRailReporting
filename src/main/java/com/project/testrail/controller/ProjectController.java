package com.project.testrail.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.testrail.controller.model.Project;
import com.project.testrail.db.model.LoadedAudit;
import com.project.testrail.db.model.LoadedAuditRepository;
import com.project.testrail.db.model.ProjectMetrics;
import com.project.testrail.db.model.ProjectMetricsRepository;

@Controller
public class ProjectController extends BaseController {
	@Autowired
	ProjectMetricsRepository projectMetricsRepository;
	@Autowired
	LoadedAuditRepository loadedAuditRepository;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("pageLogo", getPageLogoPath());
		model.addAttribute("todaysDate", getLatestDatePulled());
		model.addAttribute("projects", getProjectDetails());
		return "projects";
	}

	private String getLatestDatePulled() {
		String date = getMostRecentLoadedForDisplayFromDB();
		if (date == null) {
			return "Never";
		} else {
			return date;
		}
	}
	
	private List<Project> getProjectDetails() {
		List<ProjectMetrics> projectMetricsList = getProjectMetricsFromDB();
		List<Project> projectList = new ArrayList<Project>();

		if (projectMetricsList == null) {
			// return an empty list if db isn't populated
			return projectList;
		} else {
			Project project;
			for (ProjectMetrics projectMetrics : projectMetricsList) {
				project = new Project();
				project.setProjectId(projectMetrics.getProjectId());
				project.setProjectName(projectMetrics.getProjectName());
				project.setTotalTcs(projectMetrics.getTotalCases());
				project.setTotalAutoTcs(projectMetrics.getTotalAutomated());
				project.setNotAutomatableTcs(projectMetrics.getNotAutomatable());
				project.setAutoPercentage(projectMetrics.getPercentAutomated() + "%");

				projectList.add(project);
			}
			return projectList;
		}
	}
	
	private String getMostRecentLoadedForDisplayFromDB() {
		List<LoadedAudit> loadedAuditList = loadedAuditRepository.findByOrderByCreatedDateDesc();
		if (loadedAuditList.size() == 0) {
			return null;
		} else {
			LoadedAudit loadedAudit = loadedAuditList.get(0);
			return loadedAudit.getCreatedDateDisplay();
		}
	}
	
	private Date getMostRecentLoadedFromDB() {
		//Sort sort = new Sort(Sort.Direction.DESC, LoadedAuditRepository.createdDate);
		List<LoadedAudit> loadedAuditList = loadedAuditRepository.findByOrderByCreatedDateDesc();
		if (loadedAuditList.size() == 0) {
			return null;
		} else {
			LoadedAudit loadedAudit = loadedAuditList.get(0);
			return loadedAudit.getCreatedDate();
		}
	}

	private List<ProjectMetrics> getProjectMetricsFromDB() {
		Date lastLoaded = getMostRecentLoadedFromDB();
		if(lastLoaded == null) {
			return null;
		}
		
		List<ProjectMetrics> projectMetricsList = projectMetricsRepository.findByCreatedDate(lastLoaded);
		
		if (projectMetricsList.size() == 0) {
			return null;
		} else {
			return projectMetricsList;
		}
	}
}
