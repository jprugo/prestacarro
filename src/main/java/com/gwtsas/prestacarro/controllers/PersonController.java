package com.gwtsas.prestacarro.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.gwtsas.prestacarro.schemas.PersonSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
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

import com.gwtsas.prestacarro.entities.Person;
import com.gwtsas.prestacarro.models.PersonModelAssembler;
import com.gwtsas.prestacarro.services.impl.PersonServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/persons")
public class PersonController {

	public PersonServiceImpl personServiceImpl;

	public PersonModelAssembler personModelAssembler;

	public PagedResourcesAssembler<Person> pagedResourceAssembler;

	@Autowired
	public PersonController(PersonServiceImpl personServiceImpl, PersonModelAssembler personModelAssembler){
		this.personServiceImpl = personServiceImpl;
		this.personModelAssembler = personModelAssembler;
		//this.pagedResourceAssembler = pagedResourceAssembler;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "100") Integer pageSize) {

		Page<Person> resultList = personServiceImpl.getAll(pageNumber, pageSize);

		if (resultList.hasContent()) {
			return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
					.body(pagedResourceAssembler.toModel(resultList, personModelAssembler));
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> createPerson(@RequestBody PersonSchema personSchema) {

		Person person;

		try {
			person = personServiceImpl.createPerson(personSchema);
			return ResponseEntity.created(URI.create("/person/" + String.valueOf(person.getId()))).body(person);
		} catch (DataIntegrityViolationException exception) {
			person = personServiceImpl.getPersonByDocumentNumber(personSchema.getDocumentNumber());
			return ResponseEntity.status(208).body(person);
		}

	}

	@GetMapping
	public ResponseEntity<Person> getPersonByDocument(@RequestParam String documentNumber) {
		Person person = personServiceImpl.getPersonByDocumentNumber(documentNumber);
		return ResponseEntity.ok(person);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		Person person = personServiceImpl.getPersonaById(id);
		return ResponseEntity.ok(person);
	}

	@PutMapping
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) {
		try {
			person = personServiceImpl.updatePerson(person);
			return ResponseEntity.ok(person);
		} catch (Exception exception) {
			return ResponseEntity.notFound().build();
		}
	}

}
