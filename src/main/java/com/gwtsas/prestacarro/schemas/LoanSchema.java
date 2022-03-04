package com.gwtsas.prestacarro.schemas;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class LoanSchema implements Serializable {

	private static final long serialVersionUID = 3285886385102136866L;
	
	@Min(1)
	@NotNull
	private Long idActive;
	
	@Min(1)
	@NotNull
	private Long idPerson;

	public Long getIdActive() {
		return idActive;
	}

	public void setIdActive(Long idActive) {
		this.idActive = idActive;
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}
}
