package com.gwtsas.prestacarro.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import java.util.List;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.schemas.LoanSchema;


public interface LoanService {
	Page<Loan> getAll(int pageNumber, int pageSize);
	Loan getLoanById(Long id);
	Loan createLoan(LoanSchema loanSchema);
	Loan getLastActiveLoan(String internalCode);
	List<Loan> getLoansBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
	Loan updateLoan(Loan loan);
}
