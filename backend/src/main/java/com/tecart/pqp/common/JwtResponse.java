package com.tecart.pqp.common;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7110606198226923942L;

	private final String jwttoken;

	public JwtResponse(String jwttoken) {

		this.jwttoken = jwttoken;

	}

	public String getToken() {

		return this.jwttoken;

	}
}