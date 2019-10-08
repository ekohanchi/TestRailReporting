package com.project.testrail.db.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

 public interface LoadedAuditRepository extends JpaRepository<LoadedAudit, Integer> {
	public static String createdDate = "createdDate";
	
	List<LoadedAudit> findByOrderByCreatedDateDesc();
			
}
