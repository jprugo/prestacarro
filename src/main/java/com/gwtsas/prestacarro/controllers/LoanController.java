package com.gwtsas.prestacarro.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.gwtsas.prestacarro.components.ReportLoan;
import com.gwtsas.prestacarro.services.impl.LoansReportGeneratorImpl;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
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

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;
import com.gwtsas.prestacarro.models.LoanModelAssembler;
import com.gwtsas.prestacarro.schemas.LoanSchema;
import com.gwtsas.prestacarro.services.impl.LoanServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

	public LoanServiceImpl loanServiceImpl;

	public LoanModelAssembler loanModelAssembler;

	public PagedResourcesAssembler<Loan> pagedResourceAssembler;

	public LoansReportGeneratorImpl loansReportGenerator;

	private static Logger LOGGER = LoggerFactory.getLogger(LoansReportGeneratorImpl.class);

	@Autowired
	public LoanController(LoanServiceImpl loanServiceImpl, LoanModelAssembler loanModelAssembler, LoansReportGeneratorImpl loansReportGenerator) {
		this.loanServiceImpl = loanServiceImpl;
		this.loanModelAssembler = loanModelAssembler;
		//this.pagedResourceAssembler = pagedResourceAssembler;
		this.loansReportGenerator = loansReportGenerator;
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "100") Integer pageSize) {

		Page<Loan> resultList = loanServiceImpl.getAll(pageNumber, pageSize);

		System.out.println(resultList.getTotalElements());

		if (resultList.hasContent()) {
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
		return ResponseEntity.created(URI.create("/loan/" + String.valueOf(loan.getId()))).body(loan);
	}

	@PutMapping("/complete-last")
	public ResponseEntity<?> completeLastLoan(@Valid @RequestParam String internalCode) {
		var loan = loanServiceImpl.getLastActiveLoan(internalCode);
		loan.setReturnObject(Return.builder().loan(loan).build());
		loan = loanServiceImpl.updateLoan(loan);
		return ResponseEntity.ok(loan);
	}

	@GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[] getReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate startDate,
									   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws JRException, IOException {

		File pdf = File.createTempFile("loans-output.", ".pdf");

		FileOutputStream pdfOutputStream = new FileOutputStream(pdf);

		List<ReportLoan> loans =  loanServiceImpl.mapToReportClass(loanServiceImpl.getLoansBetweenDates(startDate.atStartOfDay(), endDate.atStartOfDay()));

		loans.stream().forEach(myPojo -> LOGGER.info(myPojo.toString()));

		loansReportGenerator.generateReport(loans, pdfOutputStream);

		LOGGER.info("La ruta es: " + pdf.getAbsolutePath());

		return FileUtils.readFileToByteArray(pdf);
	}

}
