package com.volkonovskij.controller.converter.schedules;

import com.volkonovskij.controller.requests.schedules.SchedulesUpdateRequest;
import com.volkonovskij.domain.hibernate.Schedule;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SchedulesUpdateConverter extends SchedulesBaseConverter<SchedulesUpdateRequest, Schedule> {

    private final SchedulesRepository repository;

    @Override
    public Schedule convert(SchedulesUpdateRequest source) {

        Optional<Schedule> patient = repository.findById(source.getId());
        return doConvert(patient.orElseThrow(EntityNotFoundException::new), source);
    }
}
