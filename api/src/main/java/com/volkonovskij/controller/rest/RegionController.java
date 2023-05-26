package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.controller.requests.region.RegionUpdateRequest;
import com.volkonovskij.domain.Region;
import com.volkonovskij.repository.RegionsDataRepository;
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
@RequestMapping("/rest/regions")
@RequiredArgsConstructor
public class RegionController {

    private final RegionsDataRepository regionsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all regions",
            description = "Find All Regions without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Regions",
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
            summary = "Spring Data Region Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Region",
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
            description = "Find the region by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Region",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Region.class))
                    )
            }
    )
    @GetMapping("/{regionId}")
    public ResponseEntity<Object> getRegionById(@Parameter(name = "regionId", example = "1", required = true) @PathVariable Long regionId) {

        Optional<Region> region = regionsRepository.findById(regionId);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active regions",
            description = "Search for all active regions",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Regions",
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

    @PostMapping
    public ResponseEntity<Object> saveRegion(@Valid @RequestBody RegionCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Region region = conversionService.convert(request, Region.class);

        region = regionsRepository.save(region);

        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateRegion(@RequestBody RegionUpdateRequest request) {

        Region region = conversionService.convert(request, Region.class);

        region = regionsRepository.save(region);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRegion(@PathVariable Long id) {

        Optional<Region> region = regionsRepository.findById(id);

        regionsRepository.findById(id);

        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }
}