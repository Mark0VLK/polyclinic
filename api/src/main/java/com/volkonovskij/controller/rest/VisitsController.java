package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.visit.VisitCreateRequest;
import com.volkonovskij.controller.requests.visit.VisitUpdateRequest;
import com.volkonovskij.domain.hibernate.Visit;
import com.volkonovskij.repository.springdata.VisitsRepository;
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
@RequestMapping("/rest/springdata/visits")
@RequiredArgsConstructor
public class VisitsController {

    private final VisitsRepository visitsRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllVisits() {
        List<Visit> visits = visitsRepository.findAll();
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
