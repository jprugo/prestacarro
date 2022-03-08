package com.gwtsas.prestacarro.services.impl;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.helpers.ExcelHelper;
import com.gwtsas.prestacarro.repositories.LoanRepository;
import com.gwtsas.prestacarro.schemas.LoanSchema;
import com.gwtsas.prestacarro.services.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	public LoanRepository loanRepository;

	@Autowired
	public PersonServiceImpl personServiceImpl;

	@Autowired
	public ActiveServiceImpl activeServiceImpl;

	@Override
	public Page<Loan> getAll(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Loan> pageResponse = loanRepository.findAll(pageable);
		return pageResponse;
	}

	@Override
	public ByteArrayInputStream getExcelFile(LocalDate startDate, LocalDate endDate) {
		List<Loan> loans = loanRepository.getLoansBetweenDates(startDate, endDate);
		ByteArrayInputStream in = ExcelHelper.loansToExcel(loans);
		return in;
	}

	@Override 
	public Loan getLoanById(Long id){
		return loanRepository.findById(id).get();
	}

	@Override
	public Loan createLoan(LoanSchema jsonLoan) {
		Loan loan = new Loan(
				//Active
			activeServiceImpl.getActiveById(jsonLoan.getIdActive()),
			// Person
			personServiceImpl.getPersonaById(jsonLoan.getIdPerson())
		);
		return loanRepository.save(loan);
	}

	@Override
	public Loan getLastActiveLoan(String internalCode) {
		return loanRepository.getLastActiveLoan(internalCode).get();
	}

	@Override	
	public Loan updateLoan(Loan loan) {
		return loanRepository.save(loan);
	}
	
}
