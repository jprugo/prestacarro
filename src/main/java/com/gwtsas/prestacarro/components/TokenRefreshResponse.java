package com.gwtsas.prestacarro.components;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TokenRefreshResponse {

	private String accessToken;
	private String refreshToken;
	//private String tokenType = "Bearer";

}
