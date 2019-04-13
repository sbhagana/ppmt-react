package com.ppmtoolfullstack.ppmt.exceptions;

public class InvailidLoginResponse {
	private String username;
	private String password;
	/**
	 * @param username
	 * @param password
	 */
	public InvailidLoginResponse() {
		super();
		this.username = "Invailid username";
		this.password = "Invailid password";
	}
	
	

}
