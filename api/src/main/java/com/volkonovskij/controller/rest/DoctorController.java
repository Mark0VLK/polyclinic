package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.doctor.DoctorCreateRequest;
import com.volkonovskij.controller.requests.doctor.DoctorUpdateRequest;
import com.volkonovskij.domain.hibernate.Doctor;
import com.volkonovskij.repository.springdata.DoctorsRepository;
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
@RequestMapping("/rest/springdata/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorsRepository doctorsRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllDoctors() {
        List<Doctor> doctors = doctorsRepository.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveDoctor(@Valid @RequestBody DoctorCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Doctor doctor = conversionService.convert(request, Doctor.class);

        doctor = doctorsRepository.save(doctor);

        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateDoctor(@RequestBody DoctorUpdateRequest request) {

        Doctor doctor = conversionService.convert(request, Doctor.class);

        doctor = doctorsRepository.save(doctor);

        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}
