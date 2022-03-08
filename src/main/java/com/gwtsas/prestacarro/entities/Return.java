package com.gwtsas.prestacarro.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private LocalDateTime registrationDate = LocalDateTime.now();
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	private Loan loan;
	
	public Loan getLoan() {
		return loan;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
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
