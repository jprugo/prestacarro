package com.gwtsas.prestacarro.schemas;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

@Data
public class ActiveSchema implements Serializable {

	private static final long serialVersionUID = 3285886385102136866L;

	@NotBlank
	private String internalCode;
	
	@NotBlank
	private String serial;

}
