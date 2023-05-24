package com.volkonovskij.controller.requests.patient;

import com.volkonovskij.domain.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the patient")
public class PatientCreateRequest {

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "Mark",
        description = "patient's name"
    )
    @NotNull
    @Size(min = 2, max = 15)
    private String name;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "Volkonovskij",
        description = "patient's last name"
    )
    @NotNull
    @Size(min = 2, max = 20)
    private String surname;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Gender",
        example = "NOT_SELECTED",
        description = "patient's gender"
    )
    @NotNull
    private Gender gender;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Timestamp",
        example = "1684871787000",
        description = "patient's birth date"
    )
    @NotNull
    @Pattern(regexp = "\\d+0{3}")
    private Timestamp birthDate;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "154 Ozheshko Street, apartment 15",
        description = "patient's address"
    )
    @NotNull
    @Size(min = 2, max = 40)
    private String address;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Long",
        example = "54",
        description = "patient's region number"
    )
    @NotNull
    private Long regionNumber;
}
