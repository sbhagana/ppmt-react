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
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException(
					"Project ID: " + project.getProjectIdentifier().toUpperCase() + " already exists");
		}
	}

	public Project findProjectByProjectIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null)
			throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' doesn't exists");

		return project;
	}
	
	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}
	
	public void deleteByProjectId(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null)
			throw new ProjectIdException("Can't delete the project. Project Id '"  + projectId.toUpperCase() + "' doesn't exists");
		projectRepository.delete(project);
	}
	
	public Project updateProject(Project projectToUpdate) {
		
		Project project =  projectRepository.findByProjectIdentifier(projectToUpdate.getProjectIdentifier().toUpperCase());
		
		if(project == null)
			throw new ProjectIdException("Can't update the project. Project Id '"  + projectToUpdate.getProjectIdentifier().toUpperCase() + "' doesn't exists");
		
		
		project.setDescription(projectToUpdate.getDescription());
		project.setEnd_date(projectToUpdate.getEnd_date());
		project.setProjectName(projectToUpdate.getProjectName());
		project.setStart_date(projectToUpdate.getStart_date());
		
		projectRepository.save(project);
		return project;
	}

}
