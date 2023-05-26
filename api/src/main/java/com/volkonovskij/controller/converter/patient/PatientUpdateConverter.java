package com.volkonovskij.controller.converter.patient;

import com.volkonovskij.controller.requests.patient.PatientUpdateRequest;
import com.volkonovskij.domain.Patient;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientUpdateConverter extends PatientBaseConverter<PatientUpdateRequest, Patient> {

    private final PatientsRepository repository;

    @Override
    public Patient convert(PatientUpdateRequest request) {

        Optional<Patient> patient = repository.findById(request.getCardNumber());
        patient.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(patient.orElseThrow(EntityNotFoundException::new), request);
    }
}
