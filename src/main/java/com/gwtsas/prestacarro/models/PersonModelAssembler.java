package com.gwtsas.prestacarro.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gwtsas.prestacarro.controllers.PersonController;
import com.gwtsas.prestacarro.entities.Person;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

	@Override
	public EntityModel<Person> toModel(Person entity) {
		return EntityModel.of(entity,
                linkTo(methodOn(PersonController.class).getPersonById(entity.getId()))
                        .withSelfRel());
	}

	
}