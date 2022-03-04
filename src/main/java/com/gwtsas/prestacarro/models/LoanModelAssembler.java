package com.gwtsas.prestacarro.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gwtsas.prestacarro.controllers.LoanController;
import com.gwtsas.prestacarro.entities.Loan;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LoanModelAssembler implements RepresentationModelAssembler<Loan, EntityModel<Loan>> {

	@Override
	public EntityModel<Loan> toModel(Loan entity) {
		return EntityModel.of(entity,
                linkTo(methodOn(LoanController.class).getLoanById(entity.getId()))
                        .withSelfRel());
	}

}