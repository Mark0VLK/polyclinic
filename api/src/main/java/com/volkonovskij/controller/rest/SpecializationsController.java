package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.specialization.SpecializationCreateRequest;
import com.volkonovskij.controller.requests.specialization.SpecializationUpdateRequest;
import com.volkonovskij.domain.hibernate.Specialization;
import com.volkonovskij.repository.springdata.SpecializationsRepository;
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
@RequestMapping("/rest/springdata/specializations")
@RequiredArgsConstructor
public class SpecializationsController {

    private final SpecializationsRepository specializationsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all specializations",
            description = "Find All Specializations without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Specializations",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Specialization.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllSpecializations() {
        List<Specialization> specializations = specializationsRepository.findAll();
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data Specialization Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Specialization",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                specializationsRepository.findAll(PageRequest.of(page,1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find a specialization",
            description = "Find the specialization by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Specialization",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Specialization.class))
                    )
            }
    )
    @GetMapping("/{specializationId}")
    public ResponseEntity<Object> getSpecializationById(@Parameter(name = "specializationId", example = "1", required = true) @PathVariable Long specializationId) {

        Optional<Specialization> specialization = specializationsRepository.findById(specializationId);

        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active specializations",
            description = "Search for all active specializations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Specializations",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleSpecializations() {

        Map<String, List<Specialization>> specializations = Collections.singletonMap("result", specializationsRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(specializations, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> saveSpecialization(@Valid @RequestBody SpecializationCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Specialization specialization = conversionService.convert(request, Specialization.class);

        specialization = specializationsRepository.save(specialization);

        return new ResponseEntity<>(specialization, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateSpecialization(@RequestBody SpecializationUpdateRequest request) {

        Specialization specialization = conversionService.convert(request, Specialization.class);

        specialization = specializationsRepository.save(specialization);

        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestBody SpecializationUpdateRequest request) {

        Specialization specialization = conversionService.convert(request, Specialization.class);

        specializationsRepository.delete(specialization);

        return new ResponseEntity<>(specialization, HttpStatus.CREATED);
    }
}