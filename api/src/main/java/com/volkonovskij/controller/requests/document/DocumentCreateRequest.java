package com.volkonovskij.controller.requests.document;

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
public class DocumentCreateRequest {

    @NotNull
    private String passportSeries;

    @NotNull
    private Long passportNumber;

    @NotNull
    private Timestamp expirationDate;
}
