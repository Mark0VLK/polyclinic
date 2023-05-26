package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.doctor.DoctorCreateRequest;
import com.volkonovskij.controller.requests.doctor.DoctorUpdateRequest;
import com.volkonovskij.domain.Doctor;
import com.volkonovskij.repository.DoctorsRepository;
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
import org.springframework.web.bind.annotation.DeleteMapping;
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
import java.util.Optional;

@RestController
@RequestMapping("/rest/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorsRepository doctorsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all doctors",
            description = "Find All Doctors without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Doctors",
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
            summary = "Spring Data Doctor Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Doctor",
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
            description = "Find the doctor by his id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Doctor",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Doctor.class))
                    )
            }
    )
    @GetMapping("/{doctorId}")
    public ResponseEntity<Object> getDoctorById(@Parameter(name = "doctorId", example = "1", required = true) @PathVariable Long doctorId) {

        Optional<Doctor> doctor = doctorsRepository.findById(doctorId);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active doctors",
            description = "Search for all active doctors",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Doctors",
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

    @PostMapping
    public ResponseEntity<Object> saveDoctor(@Valid @RequestBody DoctorCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Doctor doctor = conversionService.convert(request, Doctor.class);

        doctor = doctorsRepository.save(doctor);

        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateDoctor(@RequestBody DoctorUpdateRequest request) {

        Doctor doctor = conversionService.convert(request, Doctor.class);

        doctor = doctorsRepository.save(doctor);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable Long id) {

        Optional<Doctor> doctor = doctorsRepository.findById(id);

        doctorsRepository.deleteById(id);

        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }
}
