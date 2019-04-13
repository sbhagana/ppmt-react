package com.ppmtoolfullstack.ppmt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UsernameAlreadyExistsResponse {

	private String username;

	/**
	 * @param username
	 */
	public UsernameAlreadyExistsResponse(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
