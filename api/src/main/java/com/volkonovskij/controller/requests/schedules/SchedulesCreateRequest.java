package com.volkonovskij.controller.requests.schedules;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the doctor's appointment schedule for the day")
public class SchedulesCreateRequest {

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Timestamp",
        example = "1684956507000",
        description = "date"
    )
    @NotNull
    @Pattern(regexp = "\\d+0{3}")
    private Timestamp date;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Timestamp",
        example = "1684956507000",
        description = "reception start time"
    )
    @NotNull
    @Pattern(regexp = "\\d+0{3}")
    private Timestamp timeStart;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "Timestamp",
        example = "1684956507000",
        description = "reception end time"
    )
    @NotNull
    @Pattern(regexp = "\\d+0{3}")
    private Timestamp timeFinish;
}
