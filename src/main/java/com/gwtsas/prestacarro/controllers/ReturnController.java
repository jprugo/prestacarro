package com.gwtsas.prestacarro.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.schemas.ReturnSchema;
import com.gwtsas.prestacarro.services.impl.ReturnServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/returns")
public class ReturnController {

	@Autowired
	public ReturnServiceImpl returnServiceImpl;

	@PostMapping
	public ResponseEntity<?> createReturn(@RequestBody ReturnSchema returnSchema) throws JsonProcessingException {
		Return returnObject = returnServiceImpl.createReturn(returnSchema);
		return ResponseEntity.created(URI.create("/return/" + String.valueOf(returnObject.getId()))).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Return> getReturnById(@PathVariable Long id) {
		try {
			Return returnObj = returnServiceImpl.getReturnById(id);
			return ResponseEntity.ok(returnObj);
		} catch (Exception exception) {
			return ResponseEntity.notFound().build();
		}
	}

}
