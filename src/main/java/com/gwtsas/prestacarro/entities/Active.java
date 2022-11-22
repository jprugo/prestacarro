package com.gwtsas.prestacarro.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Table(name = "tbl_active")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@Builder(toBuilder = true)
// BUILDER SETTING
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// JPA SETTING
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Active implements Serializable {
	
	private static final long serialVersionUID = -6671907646833029124L;

	@Id
	@Column(name = "id_active")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "internal_code", nullable = false)
	private String internalCode;

	private String serial;

	@Column(name= "purchase_date")
    private LocalDate purchaseDate;

	@Column(
			name= "active_registration_date",
			nullable= false, 
			columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP()"
	)
	@Builder.Default
	private LocalDate registrationDate = LocalDate.now();

	@JsonIgnore
	@OneToMany(mappedBy="active", fetch = FetchType.LAZY)
	private List<Loan> loans;

	private boolean isDisabled;
}
