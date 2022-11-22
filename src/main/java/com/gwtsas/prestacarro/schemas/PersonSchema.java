package com.gwtsas.prestacarro.schemas;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PersonSchema {

    @NotBlank(message = "The first name should not be empty")
    private String firstName;

    private String middleName;

    @NotBlank(message = "The last name should not be empty")
    private String lastName;

    private String surName;

    @NotBlank(message = "The document should not be empty")
    private String documentNumber;

    @NotBlank(message = "Document number should not be empty")
    private String birthDate;

    @NotBlank(message = "Sex name should not be empty")
    private String sex;
}
