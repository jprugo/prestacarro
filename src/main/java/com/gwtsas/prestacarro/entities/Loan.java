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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_loan")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Loan implements Serializable {
	
	private static final long serialVersionUID = 1973338459522680796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_loan")
	private Long id;

	
	@ManyToOne(
			//cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
			cascade = CascadeType.MERGE,
			fetch = FetchType.EAGER
	)
	@JoinColumn(
			name = "id_active",
			referencedColumnName = "id_active",
			nullable = false
	)
	private Active active;

	@ManyToOne(
			//cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
			cascade = CascadeType.MERGE,
			fetch = FetchType.EAGER
	)
	@JoinColumn(
			name = "id_person",
			referencedColumnName = "id_person",
			nullable = false
	)
	private Person person;

	@Column(
			name = "loan_registration_date", 
			nullable = false, 
			columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()"
	)
	private LocalDate registrationDate = LocalDate.now();
	
	
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(
			name = "id_loan",
			referencedColumnName = "id_loan",
			nullable = false
	)
	private Return returnObject;
	
	//

	public Long getId() {
		return id;
	}

	public Active getActive() {
		return active;
	}

	public Person getPerson() {
		return person;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public Return getReturnObject() {
		return returnObject;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setActive(Active active) {
		this.active = active;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setReturnObject(Return returnObject) {
		this.returnObject = returnObject;
	}
	
	// Constructors
	public Loan(Active active, Person person) {
		this.active = active;
		this.person = person;
	}

	protected Loan() {
		super();
	}
	
	
	
	
}
