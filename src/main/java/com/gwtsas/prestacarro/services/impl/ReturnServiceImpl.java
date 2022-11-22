package com.gwtsas.prestacarro.services.impl;

import com.gwtsas.prestacarro.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.repositories.ReturnRepository;
import com.gwtsas.prestacarro.schemas.ReturnSchema;
import com.gwtsas.prestacarro.services.ReturnService;

@Service
public class ReturnServiceImpl implements ReturnService {

	public ReturnRepository returnRepository;

	public LoanServiceImpl loanServiceImpl;

	@Autowired
	public ReturnServiceImpl(ReturnRepository returnRepository, LoanServiceImpl loanServiceImpl) {
		this.returnRepository = returnRepository;
		this.loanServiceImpl = loanServiceImpl;
	}

	@Override
	public Return createReturn(ReturnSchema returnSchema) {
		Return returnObject = Return.builder().loan(loanServiceImpl.getLoanById(returnSchema.getIdLoan())).build();
		return returnRepository.save(returnObject);
	}

	@Override
	public Return getReturnById(Long id) {
		return returnRepository.findById(id).get();
	}

	@Override
	public Return getReturnByLoan(Long idLoan) {
		return returnRepository.getReturnByLoan(loanServiceImpl.getLoanById(idLoan));
	}

}
