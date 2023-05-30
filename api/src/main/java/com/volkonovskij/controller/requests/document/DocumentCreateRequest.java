package com.volkonovskij.controller.requests.document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about user documents")
public class DocumentCreateRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "KH",
            description = "passport series"
    )
    @Pattern(regexp = "^[A-Z]{2}$")
    @NotNull
    private String passportSeries;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            example = "1237654",
            description = "passport number"
    )
    @Pattern(regexp = "^[0-9]{7}$")
    @NotNull
    private String passportNumber;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "7637905A001PB6",
            description = "passport identification number"
    )
    @Pattern(regexp = "^[0-9]{7}[A-Z]{1}[0-9]{3}[A-Z]{2}[0-9]{1}$")
    @NotNull
    private String identificationNo;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1684956507000",
            description = "passport expiration date"
    )
    @NotNull
    private Timestamp expirationDate = Timestamp.valueOf(LocalDateTime.now());

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "2",
            description = "id of the user who owns this document"
    )
    @NotNull
    private Long userId;
}
