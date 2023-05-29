package com.volkonovskij.controller.requests.search;

import com.volkonovskij.domain.system.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the patient's gender and date of birth")
public class SearchByGenderAndDate {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Gender",
            example = "NOT_SELECTED",
            description = "patient's gender"
    )
    @NotNull
    private Gender gender = Gender.NOT_SELECTED;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1684871787000",
            description = "patient's birth date"
    )
    @NotNull
    private Timestamp birthDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
