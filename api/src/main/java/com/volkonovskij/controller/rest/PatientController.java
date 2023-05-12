package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.controller.requests.user.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernatePatient;
import com.volkonovskij.repository.springdata.PatientsRepository;
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
@RequestMapping("/rest/springdata/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientsRepository patientsRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllPatients() {
        List<HibernatePatient> patients = patientsRepository.findAll();
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
