package com.gwtsas.prestacarro.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_person")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Person implements Serializable{
	
	private static final long serialVersionUID = -4471407844111884254L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Long id;
           
	@Column(name="first_name", nullable = false)
    @NotBlank(message = "The first name should not be empty")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
    
	@Column(name="last_name",nullable = false)
	@NotBlank(message = "The last name should not be empty")
	private String lastName;
	
	@Column(name="sur_name")
	private String surName;
	
    @NotBlank(message = "The document should not be empty")
	@Column(nullable = false, unique=true)
	private String documentNumber;
	
    @NotBlank(message = "Document number should not be empty")
	@Column(nullable = false)
	private String birthDate; 
	
	@Column(nullable = false)
    @NotBlank(message = "Sex name should not be empty")
	private String sex;

	@Column(
			name= "person_registration_date",
			nullable= false,
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP()"
	)
	private LocalDateTime registrationDate = LocalDateTime.now();
	

	@OneToMany(mappedBy="person", fetch = FetchType.LAZY)
	private List<Loan> loans;

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSurName() {
		 return surName;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	
	public String getSex() {
		return sex;
	}

	public String getFullName() {
		
		 var stringBuilder = new StringBuilder();
		 
		 stringBuilder
		 	.append(firstName)
		 	.append(middleName != null ? " " + middleName +  " " : " ")
		 	.append(lastName)
		 	.append(surName == null ? "" : " " + surName)
		 	;
		 
		return stringBuilder.toString();
	}

}
