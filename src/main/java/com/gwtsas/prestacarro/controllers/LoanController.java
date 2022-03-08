package com.gwtsas.prestacarro.controllers;

import java.net.URI;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.models.LoanModelAssembler;
import com.gwtsas.prestacarro.schemas.LoanSchema;
import com.gwtsas.prestacarro.services.impl.LoanServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

	@Autowired
	public LoanServiceImpl loanServiceImpl;

	@Autowired
	public LoanModelAssembler loanModelAssembler;

	@Autowired
	public PagedResourcesAssembler<Loan> pagedResourceAssembler;

	/*@Autowired
	public PageConfiguration pageConfiguration;*/

	@GetMapping("/all")
	public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "100") Integer pageSize) {

		Page<Loan> resultList = loanServiceImpl.getAll(pageNumber, pageSize);

		System.out.println(resultList.getTotalElements());

		if (resultList.hasContent()) {
			System.out.println("Si hay content");
			return ResponseEntity.ok().contentType(MediaTypes.HAL_JSON)
					.body(pagedResourceAssembler.toModel(resultList, loanModelAssembler));
		} else {
			return ResponseEntity.noContent().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
		Loan loan = loanServiceImpl.getLoanById(id);
		return ResponseEntity.ok(loan);
	}

	@PostMapping
	public ResponseEntity<?> createLoan(@Valid @RequestBody LoanSchema loanJson) {
		var loan = loanServiceImpl.createLoan(loanJson);
		return ResponseEntity.created(URI.create("/loan/" + String.valueOf(loan.getId()))).build();
	}

	@GetMapping("/download")
	public ResponseEntity<?> getFile(
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		String filename = "loans.xlsx";
		InputStreamResource file = new InputStreamResource(loanServiceImpl.getExcelFile(startDate, endDate));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	
	@PutMapping("/complete-last")
	public ResponseEntity<?> completeLastLoan(@Valid @RequestParam String internalCode) {
		var loan = loanServiceImpl.getLastActiveLoan(internalCode);
		loan.setReturnObject(new Return(loan));
		loan = loanServiceImpl.updateLoan(loan);
		return ResponseEntity.ok(loan);
	}
	
}
