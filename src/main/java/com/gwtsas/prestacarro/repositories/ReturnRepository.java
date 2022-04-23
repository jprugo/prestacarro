package com.gwtsas.prestacarro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.entities.Return;

public interface ReturnRepository extends JpaRepository<Return, Long> {
	
	Return getReturnByLoan(Loan loan);

}
