package com.gwtsas.prestacarro.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_return")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Return implements Serializable {
	
	private static final long serialVersionUID = 7738306214296347980L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_return")
	private Long id;

	@Column(
			name = "return_registration_date",
			nullable= false,
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP()"
	)
	private LocalDate registrationDate = LocalDate.now();
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(
			name = "id_loan",
			referencedColumnName = "id_loan",
			nullable = false
	)
	private Loan loan;
	
	public Loan getLoan() {
		return loan;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	// Constructors
	
	public Return(Loan loan) {
		this.loan = loan;
	}
	
	protected Return() {
		super();
	}
	
	
	
}
