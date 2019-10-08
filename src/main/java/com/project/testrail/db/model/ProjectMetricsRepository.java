package com.project.testrail.db.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMetricsRepository extends JpaRepository<ProjectMetrics, Integer> {
	List<ProjectMetrics> findByCreatedDate(Date date);
			
}
