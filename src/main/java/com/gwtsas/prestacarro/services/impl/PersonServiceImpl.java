package com.gwtsas.prestacarro.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gwtsas.prestacarro.entities.Person;
import com.gwtsas.prestacarro.repositories.PersonRepository;
import com.gwtsas.prestacarro.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	public PersonRepository personRepository;

	@Override
	public List<Person> getAll() {
		List<Person> people= new ArrayList<Person>();
		personRepository.findAll().forEach(people::add);
		return people;
	}

	@Override
	public Person createPerson(Person person) {
		return personRepository.save(person);
	}

	@Override
	public Person getPersonaById(Long id) {
		return personRepository.findById(id).get();
	}

	@Override
	public Person getPersonByDocumentNumber(String documentNumber) {
		return personRepository.getByDocumentNumber(documentNumber).get();
	}

	@Override
	public Person updatePerson(Person person) {
		return person;
	}

	@Override
	public Page<Person> getAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Person> pageResponse = personRepository.findAll(pageable);
		return pageResponse; 
	}

}
