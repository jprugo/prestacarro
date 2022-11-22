package com.gwtsas.prestacarro.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gwtsas.prestacarro.components.ReportLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.repositories.LoanRepository;
import com.gwtsas.prestacarro.schemas.LoanSchema;
import com.gwtsas.prestacarro.services.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

	public LoanRepository loanRepository;

	public PersonServiceImpl personServiceImpl;

	public ActiveServiceImpl activeServiceImpl;

	@Autowired
	public LoanServiceImpl(LoanRepository loanRepository, PersonServiceImpl personServiceImpl, ActiveServiceImpl activeServiceImpl){
		this.activeServiceImpl = activeServiceImpl;
		this.personServiceImpl = personServiceImpl;
		this.loanRepository = loanRepository;
	}

	@Override
	public Page<Loan> getAll(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Loan> pageResponse = loanRepository.findAll(pageable);
		return pageResponse;
	}

	@Override
	public List<Loan> getLoansBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
		List<Loan> loans = loanRepository.getLoansBetweenDates(startDate, endDate);
		return loans;
	}

	public List<ReportLoan> mapToReportClass(List<Loan> loans){

		return loans.stream()
				.map(e ->
							ReportLoan
									.builder().
									idActive(e.getActive().getId())
									.sex(e.getPerson().getSex())
									.loanDate(Date.from(e.getRegistrationDate().toInstant(ZoneOffset.UTC)))
									.returnDate(e.getReturnObject() != null ? Date.from(e.getReturnObject().getRegistrationDate().toInstant(ZoneOffset.UTC)) : null)
									.document(e.getPerson().getDocumentNumber())
									.idLoan(e.getId())
									.build()
				).collect(Collectors.toList());
	}

	@Override 
	public Loan getLoanById(Long id){
		return loanRepository.findById(id).get();
	}

	@Override
	public Loan createLoan(LoanSchema loanSchema) {
		Loan loan = Loan.builder().active(activeServiceImpl.getActiveById(loanSchema.getIdActive())).person(personServiceImpl.getPersonaById(loanSchema.getIdPerson())).build()
;		return loanRepository.save(loan);
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
