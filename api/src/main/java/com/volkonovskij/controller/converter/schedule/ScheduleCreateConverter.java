package com.volkonovskij.controller.converter.schedule;

import com.volkonovskij.controller.requests.schedule.ScheduleCreateRequest;
import com.volkonovskij.domain.Doctor;
import com.volkonovskij.domain.Schedule;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.DoctorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScheduleCreateConverter extends ScheduleBaseConverter<ScheduleCreateRequest, Schedule> {

    private final DoctorsRepository doctorsRepository;

    @Override
    public Schedule convert(ScheduleCreateRequest request) {

        Schedule schedule = new Schedule();

        schedule.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        schedule.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<Doctor> doctor = doctorsRepository.findById(request.getDoctorID());
        schedule.setDoctor(doctor.orElseThrow(EntityNotFoundException::new));

        return doConvert(schedule, request);
    }
}
