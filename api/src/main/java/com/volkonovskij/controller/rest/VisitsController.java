package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.visit.VisitCreateRequest;
import com.volkonovskij.controller.requests.visit.VisitUpdateRequest;
import com.volkonovskij.domain.hibernate.Visit;
import com.volkonovskij.repository.springdata.VisitsRepository;
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
@RequestMapping("/rest/springdata/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsRepository visitsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all visits",
            description = "Find All Visits without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully Visits Doctors",
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
            summary = "Spring Data Visit Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Visit",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                visitsRepository.findAll(PageRequest.of(page,1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active visits",
            description = "Search for all active visits",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Visits",
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

    @PostMapping
    public ResponseEntity<Object> saveVisit(@Valid @RequestBody VisitCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Visit visit = conversionService.convert(request, Visit.class);

        visit = visitsRepository.save(visit);

        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateVisit(@RequestBody VisitUpdateRequest request) {

        Visit visit = conversionService.convert(request, Visit.class);

        visit = visitsRepository.save(visit);

        return new ResponseEntity<>(visit, HttpStatus.OK);
    }
}
