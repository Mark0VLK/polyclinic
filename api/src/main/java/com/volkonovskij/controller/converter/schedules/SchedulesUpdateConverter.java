package com.volkonovskij.controller.converter.schedules;

import com.volkonovskij.controller.requests.schedules.SchedulesUpdateRequest;
import com.volkonovskij.domain.hibernate.Schedule;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchedulesUpdateConverter extends SchedulesBaseConverter<SchedulesUpdateRequest, Schedule> {

    private final SchedulesRepository repository;

    @Override
    public Schedule convert(SchedulesUpdateRequest source) {

        Optional<Schedule> schedule = repository.findById(source.getId());
        schedule.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(schedule.orElseThrow(EntityNotFoundException::new), source);
    }
}
