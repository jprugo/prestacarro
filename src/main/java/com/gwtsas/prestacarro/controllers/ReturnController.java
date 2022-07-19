package com.gwtsas.prestacarro.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.schemas.ReturnSchema;
import com.gwtsas.prestacarro.services.impl.ReturnServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/returns")
public class ReturnController {

	@Autowired
	public ReturnServiceImpl returnServiceImpl;

	@PostMapping
	public ResponseEntity<?> createReturn(@RequestBody ReturnSchema returnSchema) throws JsonProcessingException {

		try {
			Return returnObject = returnServiceImpl.createReturn(returnSchema);
			Loan loan = returnObject.getLoan();
			loan.setReturnObject(returnObject);
			return ResponseEntity.created(URI.create("/return/" + String.valueOf(returnObject.getId()))).body(loan);
		} catch (DataIntegrityViolationException exception) {
			Return returnObject = returnServiceImpl.getReturnByLoan(returnSchema.getIdLoan());
			return ResponseEntity.status(208).body(returnObject);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Return> getReturnById(@PathVariable Long id) {
		Return returnObj = returnServiceImpl.getReturnById(id);
		return ResponseEntity.ok(returnObj);
	}

}
