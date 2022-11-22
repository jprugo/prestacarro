package com.gwtsas.prestacarro.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Table(name = "tbl_return")
//@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Data
@Builder(toBuilder = true)
// BUILDER SETTING
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// JPA SETTING
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Return implements Serializable {

	private static final long serialVersionUID = 7738306214296347980L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_return")
	private Long id;

	@Column(name = "return_registration_date", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP()")
	@Builder.Default
	private LocalDateTime registrationDate = LocalDateTime.now();

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_loan", referencedColumnName = "id_loan", nullable = false, unique = true)
	private Loan loan;
}
