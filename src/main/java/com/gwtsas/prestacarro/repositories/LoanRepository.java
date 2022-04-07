package com.gwtsas.prestacarro.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gwtsas.prestacarro.entities.Loan;

public interface LoanRepository extends PagingAndSortingRepository<Loan, Long> {

	@Query(value = "SELECT l FROM Loan l WHERE l.registrationDate BETWEEN :startDate AND :endDate")
	List<Loan> getLoansBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

	@Query(value = "select t2.* from (SELECT tl.*, RANK() OVER (partition by id_active order by loan_registration_date DESC) as \"ranking\" FROM (SELECT * FROM tbl_loan WHERE id_active = (SELECT id_active FROM tbl_active WHERE internal_code = :internalCode )) tl) t2 where ranking = 1;", nativeQuery = true)
	Optional<Loan> getLastActiveLoan(String internalCode);
}
