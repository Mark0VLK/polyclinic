package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.visit.VisitCreateRequest;
import com.volkonovskij.controller.requests.visit.VisitUpdateRequest;
import com.volkonovskij.domain.Visit;
import com.volkonovskij.repository.VisitsRepository;
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
@RequestMapping("/rest/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitsRepository visitsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all visits",
            description = "find all visits without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded visits",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Visit.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllVisits() {
        List<Visit> visits = visitsRepository.findAll();
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for visits with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded visits",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                visitsRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the visit",
            description = "find the visit by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded visit",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Visit.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getVisitById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Visit> visit = visitsRepository.findById(id);

        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active visits",
            description = "search for all active visits",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active visits",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleVisits() {

        Map<String, List<Visit>> visits = Collections.singletonMap("result", visitsRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(visits, HttpStatus.OK);

    }

    @Operation(
            summary = "Create a new visit",
            description = "create a new visit",
            parameters = {
                    @Parameter(
                            name = "status",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "false",
                                    type = "boolean",
                                    description = "status of the visit: took place or not")
                    ),
                    @Parameter(
                            name = "note",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Diagnosis: angina. Medications: cough pills Ambroxol. Recommendations: take a blood test.",
                                    type = "string",
                                    description = "doctor's notes")
                    ),
                    @Parameter(
                            name = "price",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "120.56",
                                    type = "number",
                                    description = "admission price")
                    ),
                    @Parameter(
                            name = "patientCardNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "5",
                                    type = "number",
                                    description = "id of the patient who is scheduled for this appointment")
                    ),
                    @Parameter(
                            name = "doctorId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "10",
                                    type = "number",
                                    description = "id of the doctor to whom this visit corresponds")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created visit",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Visit.class)
                            )
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<Object> saveVisit(@Parameter(hidden = true) @Valid @ModelAttribute VisitCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Visit visit = conversionService.convert(request, Visit.class);

        visit = visitsRepository.save(visit);

        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing visit",
            description = "edit an existing visit",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "32",
                                    type = "number",
                                    description = "visit id")
                    ),
                    @Parameter(
                            name = "status",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "false",
                                    type = "boolean",
                                    description = "status of the visit: took place or not")
                    ),
                    @Parameter(
                            name = "note",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Diagnosis: angina. Medications: cough pills Ambroxol. Recommendations: take a blood test.",
                                    type = "string",
                                    description = "doctor's notes")
                    ),
                    @Parameter(
                            name = "price",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "120.56",
                                    type = "number",
                                    description = "admission price")
                    ),
                    @Parameter(
                            name = "patientCardNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "5",
                                    type = "number",
                                    description = "id of the patient who is scheduled for this appointment")
                    ),
                    @Parameter(
                            name = "doctorId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "10",
                                    type = "number",
                                    description = "id of the doctor to whom this visit corresponds")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the visit information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Visit.class)
                            )
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PutMapping
    public ResponseEntity<Object> updateVisit(@Parameter(hidden = true) @Valid @ModelAttribute VisitUpdateRequest request) {

        Visit visit = conversionService.convert(request, Visit.class);

        visit = visitsRepository.save(visit);

        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a visit by id",
            description = "delete a visit by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The visit has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVisit(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Visit> visit = visitsRepository.findById(id);

        visitsRepository.deleteById(id);

        return new ResponseEntity<>(visit, HttpStatus.OK);
    }
}
