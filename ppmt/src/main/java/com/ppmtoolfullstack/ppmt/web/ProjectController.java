package com.ppmtoolfullstack.ppmt.web;

import com.ppmtoolfullstack.ppmt.Domain.Project;
import com.ppmtoolfullstack.ppmt.services.ProjectService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/project")
public class ProjectController {


    @Autowired
    ProjectService projectService;

    @PostMapping("")
    private ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
    	
    	if(result.hasErrors()) {
    		return new ResponseEntity<String>("Invailid Response", HttpStatus.BAD_REQUEST);
    	}
    		
    	
    	projectService.saveOrUpdateProject(project);
    	return new ResponseEntity<Project>(project,HttpStatus.CREATED);
    	
    }
}
