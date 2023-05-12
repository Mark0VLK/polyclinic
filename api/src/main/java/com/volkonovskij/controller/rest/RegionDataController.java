package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.controller.requests.region.RegionUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateRegion;
import com.volkonovskij.repository.springdata.RegionsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/springdata/regions")
@RequiredArgsConstructor
public class RegionDataController {

    private final RegionsDataRepository regionsDataRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllRegions() {
        List<HibernateRegion> regions = regionsDataRepository.findAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveRegion(@Valid @RequestBody RegionCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernateRegion region = conversionService.convert(request, HibernateRegion.class);

        region = regionsDataRepository.save(region);

        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateRegion(@RequestBody RegionUpdateRequest request) {

        HibernateRegion region = conversionService.convert(request, HibernateRegion.class);

        region = regionsDataRepository.save(region);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }
}
