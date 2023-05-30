package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.controller.requests.region.RegionUpdateRequest;
import com.volkonovskij.domain.Region;
import com.volkonovskij.repository.RegionsRepository;
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
@RequestMapping("/rest/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionsRepository regionsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all regions",
            description = "find all regions without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded regions",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Region.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllRegions() {
        List<Region> regions = regionsRepository.findAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for regions with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded regions",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                regionsRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the region",
            description = "find the region by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded region",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Region.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRegionById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Region> region = regionsRepository.findById(id);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active regions",
            description = "search for all active regions",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active regions",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleLocations() {

        Map<String, List<Region>> activeRegions = Collections.singletonMap("result", regionsRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(activeRegions, HttpStatus.OK);

    }

    @Operation(
            summary = "Create a new region",
            description = "create a new region",
            parameters = {
                    @Parameter(
                            name = "number",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "18",
                                    type = "number",
                                    description = "region number")
                    ),
                    @Parameter(
                            name = "addressRange",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Suvorov Street - Sovetskaya Square",
                                    type = "string",
                                    description = "the range of addresses belonging to the region")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created region",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Region.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> saveRegion(@Parameter(hidden = true) @Valid @ModelAttribute RegionCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Region region = conversionService.convert(request, Region.class);

        region = regionsRepository.save(region);

        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing region",
            description = "edit an existing region",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "8",
                                    type = "number",
                                    description = "region id")
                    ),
                    @Parameter(
                            name = "number",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "18",
                                    type = "number",
                                    description = "region number")
                    ),
                    @Parameter(
                            name = "addressRange",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Suvorov Street - Sovetskaya Square",
                                    type = "string",
                                    description = "the range of addresses belonging to the region")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the region information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Region.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateRegion(@Parameter(hidden = true) @Valid @ModelAttribute RegionUpdateRequest request) {

        Region region = conversionService.convert(request, Region.class);

        region = regionsRepository.save(region);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a region by id",
            description = "delete a region by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The region has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Region> region = regionsRepository.findById(id);

        regionsRepository.deleteById(id);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }
}
