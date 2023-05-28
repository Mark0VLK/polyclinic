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
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class SearchCriteria {

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "String",
                    example = "Mark",
                    description = "patient's name"
            )
    @NotNull
    @Size(min = 2, max = 15)
    private String name;

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "String",
                    example = "Volkonovskij",
                    description = "patient's last name"
            )
    @NotNull
    @Size(min = 2, max = 20)
    private String surname;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
