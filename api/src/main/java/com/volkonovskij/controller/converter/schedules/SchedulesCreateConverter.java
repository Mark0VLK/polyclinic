package com.volkonovskij.controller.converter.schedules;

import com.volkonovskij.controller.requests.schedules.SchedulesCreateRequest;
import com.volkonovskij.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SchedulesCreateConverter extends SchedulesBaseConverter<SchedulesCreateRequest, Schedule> {

    @Override
    public Schedule convert(SchedulesCreateRequest request) {

        Schedule schedule = new Schedule();

        schedule.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        schedule.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(schedule, request);
    }
}
