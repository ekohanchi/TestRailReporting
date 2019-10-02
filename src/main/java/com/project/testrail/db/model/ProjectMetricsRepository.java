package com.project.testrail.db.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectMetricsRepository extends MongoRepository<ProjectMetrics, String> {
	public static String createdDate = "createdDate";
	
//	public ProjectMetrics findByProjectId(int projectId);
		
}
