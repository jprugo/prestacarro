package com.gwtsas.prestacarro.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.schemas.ReturnSchema;
import com.gwtsas.prestacarro.services.impl.LoanServiceImpl;
import com.gwtsas.prestacarro.services.impl.ReturnServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/returns")
public class ReturnController {

	public ReturnServiceImpl returnServiceImpl;
	public LoanServiceImpl loanServiceImpl;

	@Autowired
	public ReturnController(ReturnServiceImpl returnServiceImpl, LoanServiceImpl loanServiceImpl){
		this.returnServiceImpl = returnServiceImpl;
		this.loanServiceImpl = loanServiceImpl;
	}

	@PostMapping
	public ResponseEntity<?> createReturn(@RequestBody ReturnSchema returnSchema) throws JsonProcessingException {

		Loan loan = null;
		try {
			loan = loanServiceImpl.getLoanById(returnSchema.getIdLoan());
			Return returnObject = returnServiceImpl.createReturn(loan);
			return ResponseEntity.created(URI.create("/return/" + returnObject.getId())).body(loan);
		} catch (DataIntegrityViolationException exception) {
			Return returnObject = returnServiceImpl.getReturnByLoan(loan);
			return ResponseEntity.status(208).body(returnObject);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Return> getReturnById(@PathVariable Long id) {
		Return returnObj = returnServiceImpl.getReturnById(id);
		return ResponseEntity.ok(returnObj);
	}
	
	@GetMapping("/returnLastActive")
	public ResponseEntity<?> returnLastActive(@Valid @RequestParam String internalCode) {
		try {
			Loan loan = loanServiceImpl.getLastActiveLoan(internalCode);
			Return returnObject = returnServiceImpl.createReturn(loan);
			return ResponseEntity.ok(returnObject);
		}catch (DataIntegrityViolationException exception) {
			return ResponseEntity.status(208).body("");
		}
		
	}

}
