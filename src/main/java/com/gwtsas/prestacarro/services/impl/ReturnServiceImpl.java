package com.gwtsas.prestacarro.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.repositories.ReturnRepository;
import com.gwtsas.prestacarro.services.ReturnService;

@Service
public class ReturnServiceImpl implements ReturnService {

	public ReturnRepository returnRepository;


	@Autowired
	public ReturnServiceImpl(ReturnRepository returnRepository, LoanServiceImpl loanServiceImpl) {
		this.returnRepository = returnRepository;
	}

	@Override
	public Return createReturn(Loan loan) {
		Return returnObject = Return.builder().loan(loan).build();
		return returnRepository.save(returnObject);
	}

	@Override
	public Return getReturnById(Long id) {
		return returnRepository.findById(id).get();
	}

	@Override
	public Return getReturnByLoan(Loan loan) {
		return returnRepository.getReturnByLoan(loan);
	}

}
