package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.doctor.DoctorCreateRequest;
import com.volkonovskij.controller.requests.doctor.DoctorUpdateRequest;
import com.volkonovskij.domain.Doctor;
import com.volkonovskij.repository.DoctorsRepository;
import com.volkonovskij.service.DoctorsService;
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
@RequestMapping("/rest/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorsRepository doctorsRepository;

    private final DoctorsService doctorsService;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all doctors",
            description = "find all doctors without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded doctors",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Doctor.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllDoctors() {
        List<Doctor> doctors = doctorsRepository.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for doctors with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded doctors",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                doctorsRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find a doctor",
            description = "find the doctor by his id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded doctor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Doctor.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctorById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Doctor> doctor = doctorsRepository.findById(id);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active doctors",
            description = "search for all active doctors",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active doctors",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllActiveDoctors() {

        Map<String, List<Doctor>> activeDoctors = Collections.singletonMap("result", doctorsRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(activeDoctors, HttpStatus.OK);

    }


    @Operation(
            summary = "Search for doctors by specialization",
            description = "find the last name and work schedule of all doctors of the chosen specialization",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Information found successfully",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/specialization/{name}")
    public ResponseEntity<Object> DoctorsBySpecialization(@Parameter(name = "name", example = "dentist", required = true) @PathVariable String name) {

        List<Object[]> result = doctorsService.DoctorsBySpecialization(name);

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @Operation(
            summary = "Create a new doctor",
            description = "create a new doctor",
            parameters = {
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "doctor's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "doctor's last name")
                    ),
                    @Parameter(
                            name = "office",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "105",
                                    type = "string",
                                    description = "doctor's office number")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "1",
                                    type = "number",
                                    description = "id of the user who is associated with this doctor")
                    ),
                    @Parameter(
                            name = "specializationId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "id of the specialization corresponding to this doctor")
                    ),
                    @Parameter(
                            name = "regionNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "5",
                                    type = "number",
                                    description = "id of the region to which this doctor belongs")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created doctor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Doctor.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> saveDoctor(@Parameter(hidden = true) @Valid @ModelAttribute DoctorCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Doctor doctor = conversionService.convert(request, Doctor.class);

        doctor = doctorsRepository.save(doctor);

        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing doctor",
            description = "edit an existing doctor",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "8",
                                    type = "number",
                                    description = "doctor's id")
                    ),
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark",
                                    type = "string",
                                    description = "doctor's name")
                    ),
                    @Parameter(
                            name = "surname",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Volkonovskij",
                                    type = "string",
                                    description = "doctor's last name")
                    ),
                    @Parameter(
                            name = "office",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "105",
                                    type = "string",
                                    description = "doctor's office number")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "1",
                                    type = "number",
                                    description = "id of the user who is associated with this doctor")
                    ),
                    @Parameter(
                            name = "specializationId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "id of the specialization corresponding to this doctor")
                    ),
                    @Parameter(
                            name = "regionNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "5",
                                    type = "number",
                                    description = "id of the region to which this doctor belongs")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the doctor's information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Doctor.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateDoctor(@Parameter(hidden = true) @Valid @ModelAttribute DoctorUpdateRequest request) {

        Doctor doctor = conversionService.convert(request, Doctor.class);

        doctor = doctorsRepository.save(doctor);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a doctor by id",
            description = "delete a doctor by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The doctor has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoctor(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Doctor> doctor = doctorsRepository.findById(id);

        doctorsRepository.deleteById(id);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
