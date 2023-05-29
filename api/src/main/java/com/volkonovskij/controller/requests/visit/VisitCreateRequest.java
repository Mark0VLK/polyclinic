package com.volkonovskij.controller.requests.visit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the patient's visit")
public class VisitCreateRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Boolean",
            example = "false",
            description = "status of the visit: took place or not"
    )
    @NotNull
    private Boolean status = false;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Diagnosis: angina. Medications: cough pills Ambroxol. Recommendations: take a blood test.",
            description = "doctor's notes"
    )
    @NotNull
    private String note;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Float",
            example = "120.56",
            description = "admission price"
    )
    @NotNull
    @Positive
    private Float price;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "5",
            description = "id of the patient who is scheduled for this appointment"
    )
    @NotNull
    private Long patientCardNumber;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "17",
            description = "id of the doctor to whom this visit corresponds"
    )
    @NotNull
    private Long doctorId;
}
