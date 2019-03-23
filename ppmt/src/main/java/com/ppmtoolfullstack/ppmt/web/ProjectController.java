package com.ppmtoolfullstack.ppmt.web;

import com.ppmtoolfullstack.ppmt.Domain.Project;
import com.ppmtoolfullstack.ppmt.services.ErrorValidationHandlerService;
import com.ppmtoolfullstack.ppmt.services.ProjectService;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@Autowired
	ErrorValidationHandlerService errorValidationHandlerService;

	@PostMapping("")
	private ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

		ResponseEntity<?> errorMap = errorValidationHandlerService.ErrorValidationHandler(result);

		if (errorMap != null)
			return errorMap;

		projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);

	}

	@GetMapping("{projectId}")
	public Project findProjectByProjectId(@PathVariable String projectId) {
		return projectService.findProjectByProjectIdentifier(projectId);
	}

	@GetMapping("/all")
	public Iterable<Project> findAllProjects() {
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("{projectId}")
	public ResponseEntity<?> deleteByProjectId(@PathVariable String projectId) {
		projectService.deleteByProjectId(projectId);
		return new ResponseEntity<String>("Project deleted successfully",HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateExistingProject(@RequestBody Project project) {
		 projectService.updateProject(project);
		
		return new ResponseEntity<String>("Project Updated Successfully",HttpStatus.OK);
	}
}
