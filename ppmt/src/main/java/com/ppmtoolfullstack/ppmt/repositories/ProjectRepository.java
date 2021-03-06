package com.ppmtoolfullstack.ppmt.repositories;

import com.ppmtoolfullstack.ppmt.Domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
	Project findByProjectIdentifier(String projectId);
	
	Iterable<Project> findAllByProjectLeader(String projectLeader);
}
