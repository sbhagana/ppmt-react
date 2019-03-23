package com.ppmtoolfullstack.ppmt.services;

import com.ppmtoolfullstack.ppmt.Domain.Project;
import com.ppmtoolfullstack.ppmt.exceptions.CustomResponseEntityExceptionHandler;
import com.ppmtoolfullstack.ppmt.exceptions.ProjectIdException;
import com.ppmtoolfullstack.ppmt.repositories.ProjectRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(@Valid Project project) {
		try {
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("This is a duplicate Project Identifier");
		}
	}

  
}
