package com.volkonovskij.controller.requests.doctor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the doctor")
public class DoctorCreateRequest {

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "String",
                    example = "Mark",
                    description = "doctor's name"
            )
    @NotNull
    @Size(min = 2, max = 15)
    private String name;

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "String",
                    example = "Volkonovskij",
                    description = "doctor's last name"
            )
    @NotNull
    @Size(min = 2, max = 20)
    private String surname;

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "String",
                    example = "105",
                    description = "doctor's office number"
            )
    @NotNull
    private String office;

    @NotNull
    private Long userId;

    @NotNull
    private Long specializationId;

    @NotNull
    private Long regionNumber;
}
