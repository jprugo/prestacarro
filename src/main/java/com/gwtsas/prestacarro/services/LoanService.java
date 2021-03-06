package com.gwtsas.prestacarro.services;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.schemas.LoanSchema;


public interface LoanService {
	
	Page<Loan> getAll(int pageNumber, int pageSize);
	
	Loan getLoanById(Long id);
	
	Loan createLoan(LoanSchema loan);
	
	Loan getLastActiveLoan(String internalCode);
	
	ByteArrayInputStream getExcelFile(LocalDateTime startDate, LocalDateTime endDate);

	Loan updateLoan(Loan loan);
}
