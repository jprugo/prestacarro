package com.gwtsas.prestacarro.models;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gwtsas.prestacarro.controllers.ActiveController;

import com.gwtsas.prestacarro.entities.Active;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ActiveModelAssembler implements RepresentationModelAssembler<Active, EntityModel<Active>> {

	@Override
	public EntityModel<Active> toModel(Active entity) {

		var response = EntityModel.of(entity,
				linkTo(methodOn(ActiveController.class).getActiveById(entity.getId())).withSelfRel());
		response.add(new Link[] { entity.isDisabled()
				? linkTo(methodOn(ActiveController.class).enableActive(null)).withRel("handleState").withType("PUT")
						.expand(entity.getId())
				: linkTo(methodOn(ActiveController.class).disableActive(null)).withRel("handleState").withType("PUT")
						.expand(entity.getId()) });
		return response;
	}

}