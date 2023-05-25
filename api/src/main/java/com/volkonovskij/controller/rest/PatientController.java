package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.controller.requests.user.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernatePatient;
import com.volkonovskij.domain.hibernate.HibernateRegion;
import com.volkonovskij.repository.springdata.PatientsRepository;
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
@RequestMapping("/rest/springdata/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientsRepository patientsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all patients",
            description = "Find All Patients without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Patients",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HibernatePatient.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllPatients() {
        List<HibernatePatient> patients = patientsRepository.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data Patient Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Patient",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                patientsRepository.findAll(PageRequest.of(page,1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active patients",
            description = "Search for all active patients",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Patients",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisiblePatients() {

        Map<String, List<HibernatePatient>> patients = Collections.singletonMap("result", patientsRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(patients, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> savePatient(@Valid @RequestBody PatientCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernatePatient hibernatePatient = conversionService.convert(request, HibernatePatient.class);

        hibernatePatient = patientsRepository.save(hibernatePatient);

        return new ResponseEntity<>(hibernatePatient, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updatePatient(@RequestBody UserUpdateRequest request) {

        HibernatePatient hibernatePatient = conversionService.convert(request, HibernatePatient.class);

        hibernatePatient = patientsRepository.save(hibernatePatient);

        return new ResponseEntity<>(hibernatePatient, HttpStatus.OK);
    }
}
