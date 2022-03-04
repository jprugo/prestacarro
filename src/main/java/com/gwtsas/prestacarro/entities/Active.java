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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tbl_active")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
	private LocalDate registrationDate = LocalDate.now();
	
	
	@OneToMany(mappedBy="active", fetch = FetchType.LAZY)
	private List<Loan> loans;
	
	private boolean isDisabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}
