package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.specialization.SpecializationCreateRequest;
import com.volkonovskij.controller.requests.specialization.SpecializationUpdateRequest;
import com.volkonovskij.domain.hibernate.Specialization;
import com.volkonovskij.repository.springdata.SpecializationsRepository;
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
@RequestMapping("/rest/springdata/specializations")
@RequiredArgsConstructor
public class SpecializationsController {

    private final SpecializationsRepository specializationsRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllSpecializations() {
        List<Specialization> specializations = specializationsRepository.findAll();
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
}
