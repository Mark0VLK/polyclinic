package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.schedule.ScheduleCreateRequest;
import com.volkonovskij.controller.requests.schedule.ScheduleUpdateRequest;
import com.volkonovskij.domain.Schedule;
import com.volkonovskij.repository.SchedulesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final SchedulesRepository schedulesRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all schedules",
            description = "find all schedules without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded schedules",
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
            summary = "Search for schedules with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded schedules",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                schedulesRepository.findAll(PageRequest.of(page, 5))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the schedule",
            description = "find the schedule by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded schedule",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Schedule.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getScheduleById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Schedule> schedule = schedulesRepository.findById(id);

        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active schedules",
            description = "search for all active schedules",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active schedules",
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

    @Operation(
            summary = "Create a new schedule",
            description = "create a new schedule",
            parameters = {
                    @Parameter(
                            name = "date",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "date")
                    ),
                    @Parameter(
                            name = "timeStart",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "reception start time")
                    ),
                    @Parameter(
                            name = "timeFinish",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "reception end time")
                    ),
                    @Parameter(
                            name = "doctorID",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "8",
                                    type = "number",
                                    description = "id of the doctor who owns this schedule")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created schedule",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Schedule.class)
                            )
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<Object> saveSchedule(@Parameter(hidden = true) @Valid @ModelAttribute ScheduleCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Schedule schedule = conversionService.convert(request, Schedule.class);

        schedule = schedulesRepository.save(schedule);

        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing schedule",
            description = "Edit an existing schedule",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "8",
                                    type = "number",
                                    description = "id schedule")
                    ),
                    @Parameter(
                            name = "date",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "date")
                    ),
                    @Parameter(
                            name = "timeStart",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "reception start time")
                    ),
                    @Parameter(
                            name = "timeFinish",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "reception end time")
                    ),
                    @Parameter(
                            name = "doctorID",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "8",
                                    type = "number",
                                    description = "id of the doctor who owns this schedule")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the schedule information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Schedule.class)
                            )
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PutMapping
    public ResponseEntity<Object> updateSchedule(@Parameter(hidden = true) @Valid @ModelAttribute ScheduleUpdateRequest request) {

        Schedule schedule = conversionService.convert(request, Schedule.class);

        schedule = schedulesRepository.save(schedule);

        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a schedule by id",
            description = "delete a schedule by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The schedule has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSchedule(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Schedule> schedule = schedulesRepository.findById(id);

        schedulesRepository.findById(id);

        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }
}
