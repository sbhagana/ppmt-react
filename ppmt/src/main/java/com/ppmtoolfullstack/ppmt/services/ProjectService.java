package com.ppmtoolfullstack.ppmt.services;

import com.ppmtoolfullstack.ppmt.Domain.Project;
import com.ppmtoolfullstack.ppmt.repositories.ProjectRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(@Valid Project project) {
        return projectRepository.save(project);
	}

  
}
