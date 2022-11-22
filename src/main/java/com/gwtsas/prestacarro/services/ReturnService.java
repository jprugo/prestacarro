package com.gwtsas.prestacarro.services;

import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.schemas.ReturnSchema;

public interface ReturnService {
	Return createReturn(ReturnSchema returnSchema);
	Return getReturnById(Long id);
	Return getReturnByLoan(Long idLoan);
}
