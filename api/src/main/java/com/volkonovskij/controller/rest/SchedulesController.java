package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.schedules.SchedulesCreateRequest;
import com.volkonovskij.controller.requests.schedules.SchedulesUpdateRequest;
import com.volkonovskij.domain.hibernate.Schedule;
import com.volkonovskij.repository.springdata.SchedulesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/springdata/schedules")
@RequiredArgsConstructor
public class SchedulesController {

    private final SchedulesRepository schedulesRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all schedules",
            description = "Find All Schedules without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Schedules",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Schedule.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllSchedules() {
        List<Schedule> schedules = schedulesRepository.findAll();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data Schedule Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Schedule",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                schedulesRepository.findAll(PageRequest.of(page,1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active schedules",
            description = "Search for all active schedules",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Schedules",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleSchedules() {

        Map<String, List<Schedule>> schedules = Collections.singletonMap("result", schedulesRepository.findByIsDeletedIsFalse());

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
