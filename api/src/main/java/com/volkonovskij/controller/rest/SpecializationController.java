package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.specialization.SpecializationCreateRequest;
import com.volkonovskij.controller.requests.specialization.SpecializationUpdateRequest;
import com.volkonovskij.domain.Specialization;
import com.volkonovskij.repository.SpecializationsRepository;
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
@RequestMapping("/rest/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationsRepository specializationsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all specializations",
            description = "find all specializations without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded specializations",
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
            summary = "Search for specializations with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded specializations",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                specializationsRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find a specialization",
            description = "find the specialization by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded specialization",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Specialization.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getSpecializationById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Specialization> specialization = specializationsRepository.findById(id);

        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active specializations",
            description = "search for all active specializations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active specializations",
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

    @Operation(
            summary = "Create a new specialization",
            description = "create a new specialization",
            parameters = {
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "traumatologist",
                                    type = "string",
                                    description = "name of the doctor's specialization")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created specialization",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Specialization.class)
                            )
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<Object> saveSpecialization(@Parameter(hidden = true) @Valid @ModelAttribute SpecializationCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Specialization specialization = conversionService.convert(request, Specialization.class);

        specialization = specializationsRepository.save(specialization);

        return new ResponseEntity<>(specialization, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing specialization",
            description = "Edit an existing specialization",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "9",
                                    type = "number",
                                    description = "specialization id")
                    ),
                    @Parameter(
                            name = "name",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "traumatologist",
                                    type = "string",
                                    description = "name of the doctor's specialization")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the specialization information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Specialization.class)
                            )
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PutMapping
    public ResponseEntity<Object> updateSpecialization(@Parameter(hidden = true) @Valid @ModelAttribute SpecializationUpdateRequest request) {

        Specialization specialization = conversionService.convert(request, Specialization.class);

        specialization = specializationsRepository.save(specialization);

        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a specialization by id",
            description = "delete a specialization by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The specialization has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Specialization> specialization = specializationsRepository.findById(id);

        specializationsRepository.deleteById(id);

        return new ResponseEntity<>(specialization, HttpStatus.CREATED);
    }
}