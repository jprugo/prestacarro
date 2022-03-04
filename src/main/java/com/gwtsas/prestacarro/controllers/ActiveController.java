package com.gwtsas.prestacarro.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gwtsas.prestacarro.entities.Active;
import com.gwtsas.prestacarro.models.ActiveModelAssembler;
import com.gwtsas.prestacarro.schemas.ActiveSchema;
import com.gwtsas.prestacarro.services.impl.ActiveServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/actives")
public class ActiveController {

	@Autowired
	public ActiveServiceImpl activeServiceImpl;

	@Autowired
	public ActiveModelAssembler activeModelAssembler;

	@GetMapping
	public ResponseEntity<?> getAll() {
		
		List<Active> resultList = activeServiceImpl.getAllActives();
		if (resultList.size() > 0) {
			
			var body  = resultList.stream().map(e -> activeModelAssembler.toModel(e)).collect(Collectors.toList());

			return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON).body(body);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Active> getActiveById(@PathVariable Long id) {
		Active active = activeServiceImpl.getActiveById(id);
		return ResponseEntity.ok(active);
	}

	@PostMapping
	public ResponseEntity<?> createActive(@Valid @RequestBody ActiveSchema activeSchema) {
		var active = activeServiceImpl.createActive(activeSchema);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(active.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}/disable")
	public ResponseEntity<?> disableActive(@PathVariable Long id) {
		return ResponseEntity.ok(activeServiceImpl.changeDisableStatus(id, true));
	}

	@PutMapping("/{id}/enable")
	public ResponseEntity<?> enableActive(@PathVariable Long id) {
		return ResponseEntity.ok(activeServiceImpl.changeDisableStatus(id, false));
	}

}
