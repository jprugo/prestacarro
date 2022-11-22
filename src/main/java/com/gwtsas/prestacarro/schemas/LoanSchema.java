package com.gwtsas.prestacarro.schemas;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class LoanSchema implements Serializable {

	private static final long serialVersionUID = 3285886385102136866L;
	
	@Min(1)
	@NotNull
	private Long idActive;
	
	@Min(1)
	@NotNull
	private Long idPerson;

}
