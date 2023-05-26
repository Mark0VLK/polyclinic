package com.volkonovskij.controller.requests.visit;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Object with information about the patient's visit")
public class VisitCreateRequest {

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Boolean",
        example = "true",
        description = "status of the visit: took place or not"
    )
    @NotNull
    private Boolean status;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "Diagnosis: angina. Medications: cough pills Ambroxol. Recommendations: take a blood test.",
        description = "doctor's notes"
    )
    @NotNull
    private String note;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Float",
        example = "120.56",
        description = "admission price"
    )
    @NotNull
    private Float price;
}
