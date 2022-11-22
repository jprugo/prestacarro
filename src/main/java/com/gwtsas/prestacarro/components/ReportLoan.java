package com.gwtsas.prestacarro.components;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Getter
@Builder(toBuilder = true)
// BUILDER SETTING
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ReportLoan {

    private Long idLoan;

    private Long idActive;

    private String document;

    private String sex;

    private Date loanDate;

    private Date returnDate;

    @Override
    public String toString() {
        return "ReportLoan{" +
                "id=" + idLoan +
                ", idActive=" + idActive +
                ", document='" + document + '\'' +
                ", sex='" + sex + '\'' +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
