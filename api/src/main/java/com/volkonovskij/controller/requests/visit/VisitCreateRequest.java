package com.volkonovskij.controller.requests.visit;

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
public class VisitCreateRequest {

    @NotNull
    private Boolean status;

    @NotNull
    private String note;

    @NotNull
    private float price;
}
