package com.volkonovskij.controller.requests.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the patient's gender and date of birth")
public class SearchByGenderAndRegion {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1685446480000",
            description = "date of birth"
    )
    @NotNull
    private Timestamp data;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "54",
            description = "region number"
    )
    @Positive
    @NotNull
    private Long number;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
