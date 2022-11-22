package com.gwtsas.prestacarro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gwtsas.prestacarro.entities.Person;


public interface PersonRepository extends PagingAndSortingRepository<Person, Long>{

	@Query("SELECT p FROM Person p WHERE p.documentNumber = ?1")
	Optional<Person> getByDocumentNumber(String documentNumber);

}
