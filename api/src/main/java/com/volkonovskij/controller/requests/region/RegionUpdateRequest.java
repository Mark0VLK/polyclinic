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
@Schema(description = "Object for changing information about the region")
public class RegionUpdateRequest extends RegionCreateRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "2",
            description = "region id"
    )
    @Positive
    @NotNull
    private Long id;
}
