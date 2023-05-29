package com.volkonovskij.controller.requests.patient;

import com.volkonovskij.domain.system.Gender;
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

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Mark",
            description = "patient's name"
    )
    @Pattern(regexp = "^[a-zA-Z]{2,15}$")
    @NotNull
    private String name;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Volkonovskij",
            description = "patient's last name"
    )
    @Pattern(regexp = "^[a-zA-Z]{2,20}$")
    @NotNull
    private String surname;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Gender",
            example = "NOT_SELECTED",
            description = "patient's gender"
    )
    @NotNull
    private Gender gender = Gender.NOT_SELECTED;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1684871787000",
            description = "patient's birth date"
    )
    @NotNull
    private Timestamp birthDate;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "154 Ozheshko Street, apartment 15",
            description = "patient's address"
    )
    @NotNull
    @Size(min = 2, max = 40)
    private String address;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "54",
            description = "id of the region the patient belongs to"
    )
    @NotNull
    private Long regionNumber;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "5",
            description = "id of the user the patient belongs to"
    )
    @NotNull
    private Long userId;


}
