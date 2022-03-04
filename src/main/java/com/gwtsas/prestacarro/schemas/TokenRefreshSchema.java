package com.gwtsas.prestacarro.schemas;

import javax.validation.constraints.NotBlank;

public class TokenRefreshSchema {
	
	@NotBlank
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	

}
