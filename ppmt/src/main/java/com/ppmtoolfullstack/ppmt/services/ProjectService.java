package com.ppmtoolfullstack.ppmt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppmtoolfullstack.ppmt.Domain.Backlog;
import com.ppmtoolfullstack.ppmt.Domain.Project;
import com.ppmtoolfullstack.ppmt.Domain.User;
import com.ppmtoolfullstack.ppmt.exceptions.ProjectIdException;
import com.ppmtoolfullstack.ppmt.exceptions.ProjectNotFoundException;
import com.ppmtoolfullstack.ppmt.repositories.BacklogRepository;
import com.ppmtoolfullstack.ppmt.repositories.ProjectRepository;
import com.ppmtoolfullstack.ppmt.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;

	@Autowired
	private UserRepository userRepository;

	public Project saveOrUpdateProject(Project project, String username) {

		if (project.getId() != null) {
			Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
			if (existingProject != null && (!existingProject.getProjectLeader().equals(username))) {
				throw new ProjectNotFoundException("Project not found in your account");
			} else if (existingProject == null) {
				throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier()
						+ "' cannot be updated because it doesn't exist");
			}
		}

		try {

			User user = userRepository.findByUsername(username);
			project.setUser(user);
			project.setProjectLeader(user.getUsername());
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			if (project.getId() == null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}

			if (project.getId() != null) {
				project.setBacklog(
						backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}

			return projectRepository.save(project);

		} catch (Exception e) {
			throw new ProjectIdException(
					"Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}

	}

	public Project findProjectByProjectIdentifier(String projectId, String username) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null)
			throw new ProjectIdException("Project ID '" + projectId.toUpperCase() + "' doesn't exists");

		if (!project.getProjectLeader().equals(username)) {
			throw new ProjectNotFoundException("Project not found in your account");
		}
		return project;
	}

	public Iterable<Project> findAllProjects(String username) {
		return projectRepository.findAllByProjectLeader(username);
	}

	public void deleteByProjectId(String projectId, String username) {
		projectRepository.delete(findProjectByProjectIdentifier(projectId, username));
	}

//	public Project updateProject(Project projectToUpdate) {
//		
//		Project project =  projectRepository.findByProjectIdentifier(projectToUpdate.getProjectIdentifier().toUpperCase());
//		
//		if(project == null)
//			throw new ProjectIdException("Can't update the project. Project Id '"  + projectToUpdate.getProjectIdentifier().toUpperCase() + "' doesn't exists");
//		
//		
//		project.setDescription(projectToUpdate.getDescription());
//		project.setEnd_date(projectToUpdate.getEnd_date());
//		project.setProjectName(projectToUpdate.getProjectName());
//		project.setStart_date(projectToUpdate.getStart_date());
//		
//		projectRepository.save(project);
//		return project;
//	}

}
