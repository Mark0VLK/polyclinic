package com.volkonovskij.controller.requests.doctor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class DoctorCreateRequest {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String office;
}
