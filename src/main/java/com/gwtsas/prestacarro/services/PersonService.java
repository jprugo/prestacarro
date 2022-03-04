package com.gwtsas.prestacarro.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gwtsas.prestacarro.entities.Person;

public interface PersonService {
	
	List<Person> getAll();
	
	Person createPerson(Person person);
	
	Person getPersonaById(Long id);
	
	Person getPersonByDocumentNumber(String documentNumber);
	
	Person updatePerson(Person person);
	
	Page<Person> getAll(int pageNumber, int pageSize);
}
