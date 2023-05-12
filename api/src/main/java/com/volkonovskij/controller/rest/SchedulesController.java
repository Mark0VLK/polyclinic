package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.schedules.SchedulesCreateRequest;
import com.volkonovskij.controller.requests.schedules.SchedulesUpdateRequest;
import com.volkonovskij.domain.hibernate.Schedule;
import com.volkonovskij.repository.springdata.SchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/springdata/schedules")
@RequiredArgsConstructor
public class SchedulesController {

    private final SchedulesRepository schedulesRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllSchedules() {
        List<Schedule> schedules = schedulesRepository.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveSchedule(@Valid @RequestBody SchedulesCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Schedule schedule = conversionService.convert(request, Schedule.class);

        schedule = schedulesRepository.save(schedule);

        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateSchedule(@RequestBody SchedulesUpdateRequest request) {

        Schedule schedule = conversionService.convert(request, Schedule.class);

        schedule = schedulesRepository.save(schedule);

        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
}
