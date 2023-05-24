package com.volkonovskij.controller.requests.region;

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
@Schema(description = "Object with information about the region")
public class RegionCreateRequest {

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Long",
        example = "54",
        description = "region number"
    )
    @NotNull
    @Positive
    private Long number;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "Suvorov Street - Sovetskaya Square",
        description = "the range of addresses belonging to the region"
    )
    @NotNull
    private String addressRange;
}
