package com.gwtsas.prestacarro.schemas;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ReturnSchema implements Serializable {

	private static final long serialVersionUID = -6644238609309427461L;
	
	@Min(1)
	@NotNull
	private Long idLoan;

}
