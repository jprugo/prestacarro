package com.gwtsas.prestacarro.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.repositories.ReturnRepository;
import com.gwtsas.prestacarro.schemas.ReturnSchema;
import com.gwtsas.prestacarro.services.ReturnService;

@Service
public class ReturnServiceImpl implements ReturnService {

	@Autowired
	public ReturnRepository returnRepository;

	@Autowired
	public LoanServiceImpl loanServiceImpl;

	@Override
	public Return createReturn(ReturnSchema returnJson) {
		Return returnObject = new Return(loanServiceImpl.getLoanById(returnJson.getIdLoan()));
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
