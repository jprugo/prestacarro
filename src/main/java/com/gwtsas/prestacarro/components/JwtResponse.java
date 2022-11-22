package com.gwtsas.prestacarro.components;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class JwtResponse {

	public static String TYPE = "Bearer";

	private String token;

	private String refreshToken;

	private Long id;

	private String username;

	private String email; 
	
	private List<String> roles;

}
