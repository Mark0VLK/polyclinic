package com.volkonovskij.controller.requests.specialization;

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
@Schema(description = "Object with information about the doctor's specialization")
public class SpecializationCreateRequest {

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "String",
                    example = "traumatologist",
                    description = "name of the doctor's specialization"
            )
    @NotNull
    @Size(min = 3, max = 30)
    private String name;
}
