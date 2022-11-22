package com.gwtsas.prestacarro.services;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;

public interface ReturnService {
	Return createReturn(Loan loan);
	Return getReturnById(Long id);
	Return getReturnByLoan(Loan loan);
}
