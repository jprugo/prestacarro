package com.gwtsas.prestacarro.schemas;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;


public class ActiveSchema implements Serializable {

	private static final long serialVersionUID = 3285886385102136866L;

	@NotBlank
	private String internalCode;
	
	@NotBlank
	private String serial;

	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}
