package com.gwtsas.prestacarro.schemas;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshSchema {
	
	@NotBlank
	private String refreshToken;

}
