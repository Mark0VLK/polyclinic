package com.volkonovskij.controller.converter.schedule;

import com.volkonovskij.controller.requests.schedule.ScheduleUpdateRequest;
import com.volkonovskij.domain.Schedule;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScheduleUpdateConverter extends ScheduleBaseConverter<ScheduleUpdateRequest, Schedule> {

    private final SchedulesRepository repository;

    @Override
    public Schedule convert(ScheduleUpdateRequest source) {

        Optional<Schedule> schedule = repository.findById(source.getId());
        schedule.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(schedule.orElseThrow(EntityNotFoundException::new), source);
    }
}
