package com.volkonovskij.controller.requests.document;

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
@Schema(description = "Object with information about user documents")
public class DocumentCreateRequest {

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "KH",
        description = "passport series"
    )
    @NotNull
    @Size(min = 2, max = 2)
    private String passportSeries;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "1237654",
        description = "passport number"
    )
    @NotNull
    @Size(min = 7, max = 7)
    private Long passportNumber;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "7637905A001PB6",
        description = "passport identification number"
    )
    @NotNull
    @Size(min = 14, max = 14)
    private String identificationNo;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Timestamp",
        example = "1684956507000",
        description = "passport expiration date"
    )
    @NotNull
    @Pattern(regexp = "\\d+0{3}")
    private Timestamp expirationDate;
}
