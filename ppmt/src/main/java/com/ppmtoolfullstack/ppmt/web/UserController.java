package com.ppmtoolfullstack.ppmt.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppmtoolfullstack.ppmt.Domain.User;
import com.ppmtoolfullstack.ppmt.payload.JwtLoginSuccessResponse;
import com.ppmtoolfullstack.ppmt.payload.LoginRequest;
import com.ppmtoolfullstack.ppmt.security.JwtTokenProvider;
import com.ppmtoolfullstack.ppmt.security.SecurityConstants;
import com.ppmtoolfullstack.ppmt.services.ErrorValidationHandlerService;
import com.ppmtoolfullstack.ppmt.services.UserService;
import com.ppmtoolfullstack.ppmt.validator.UserValidator;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private ErrorValidationHandlerService errorValidationHandlerService; 
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserValidator validator;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	 @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
	        ResponseEntity<?> errorMap = errorValidationHandlerService.ErrorValidationHandler(result);
	        if(errorMap != null) return errorMap;

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = SecurityConstants.TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

	        return ResponseEntity.ok(new JwtLoginSuccessResponse(true, jwt));
	    }

	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid  @RequestBody User user, BindingResult result ) {
		validator.validate(user, result);
		
		// Validate passwords match
		ResponseEntity<?> errorMap = errorValidationHandlerService.ErrorValidationHandler(result);
		if (errorMap != null) 
			return errorMap;
		
		User newUser = service.saveUSer(user);
		return new ResponseEntity<User>( newUser, 	HttpStatus.CREATED);
	}
	

}
