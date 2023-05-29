package com.volkonovskij.controller.requests.doctor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the doctor at the registration stage")
public class DoctorCreateRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Mark",
            description = "doctor's name"
    )
    @Pattern(regexp = "^[a-zA-Z]{2,15}$")
    @NotNull
    private String name;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Volkonovskij",
            description = "doctor's last name"
    )
    @Pattern(regexp = "^[a-zA-Z]{2,20}$")
    @NotNull
    private String surname;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "105",
            description = "doctor's office number"
    )
    @NotNull
    private String office;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "3",
            description = "id of the user who is associated with this doctor"
    )
    @NotNull
    private Long userId;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "4",
            description = "id of the specialization corresponding to this doctor"
    )
    @NotNull
    private Long specializationId;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "5",
            description = "id of the region to which this doctor belongs"
    )
    @NotNull
    private Long regionNumber;
}
