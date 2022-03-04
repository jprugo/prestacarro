package com.gwtsas.prestacarro.schemas;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReturnSchema implements Serializable {

	private static final long serialVersionUID = -6644238609309427461L;
	
	@Min(1)
	@NotNull
	private Long idLoan;

	public Long getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(Long idLoan) {
		this.idLoan = idLoan;
	}

}
