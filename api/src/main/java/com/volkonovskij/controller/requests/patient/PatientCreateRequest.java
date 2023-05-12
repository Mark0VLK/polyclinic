package com.volkonovskij.controller.requests.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class PatientCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String gender;

    @NotNull
    private Timestamp birthDate;

    @NotNull
    private String address;

//    @NotNull
//    private Long idUser;

    @NotNull
    private Long regionNumber;
}
