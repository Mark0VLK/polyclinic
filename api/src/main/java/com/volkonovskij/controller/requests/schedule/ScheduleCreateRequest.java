package com.volkonovskij.controller.requests.schedule;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the doctor's schedule")
public class ScheduleCreateRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1684956507000",
            description = "date"
    )
    @NotNull
    private Timestamp date;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1684956507000",
            description = "reception start time"
    )
    @NotNull
    private Timestamp timeStart;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Timestamp",
            example = "1684956507000",
            description = "reception end time"
    )
    @NotNull
    private Timestamp timeFinish;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "Long",
            example = "8",
            description = "id of the doctor who owns this schedule"
    )
    @NotNull
    private Long doctorID;
}
