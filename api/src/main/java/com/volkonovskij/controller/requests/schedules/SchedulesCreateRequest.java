package com.volkonovskij.controller.requests.schedules;

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
public class SchedulesCreateRequest {

    @NotNull
    private Timestamp date;

    @NotNull
    private Timestamp timeStart;

    @NotNull
    private Timestamp timeFinish;
}
