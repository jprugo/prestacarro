package com.gwtsas.prestacarro.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gwtsas.prestacarro.entities.Loan;

public interface LoanRepository extends PagingAndSortingRepository<Loan, Long> {

	@Query(value = "SELECT l FROM Loan l WHERE l.registrationDate BETWEEN :startDate AND :endDate")
	List<Loan> getLoansBetweenDates (LocalDate startDate, LocalDate endDate);
}
