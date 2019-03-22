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

    @Autowired
    ErrorValidationHandlerService errorValidationHandlerService;
    
    @PostMapping("")
    private ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
    	
    	ResponseEntity<?> errorMap = errorValidationHandlerService.ErrorValidationHandler(result);
    	
    	if(errorMap != null) return errorMap;
    	
    	projectService.saveOrUpdateProject(project);
    	return new ResponseEntity<Project>(project,HttpStatus.CREATED);
    	
    }
}
