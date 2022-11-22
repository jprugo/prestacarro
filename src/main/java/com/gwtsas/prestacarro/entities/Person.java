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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Table(name = "tbl_person")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Builder
// BUILDER SETTING
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// JPA SETTING
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Person implements Serializable{
	
	private static final long serialVersionUID = -4471407844111884254L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Long id;
           
	@Column(name="first_name", nullable = false)
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;

	@Column(name="last_name",nullable = false)
	private String lastName;
	
	@Column(name="sur_name")
	private String surName;

	@Column(nullable = false, unique=true)
	private String documentNumber;

	@Column(nullable = false)
	private String birthDate; 
	
	@Column(nullable = false)
	private String sex;

	@Column(
			name= "person_registration_date",
			nullable= false,
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP()"
	)
	@Builder.Default
	private LocalDateTime registrationDate = LocalDateTime.now();

	@OneToMany(mappedBy="person", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Loan> loans;

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
