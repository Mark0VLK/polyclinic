package com.volkonovskij.controller.converter.patient;

import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PatientCreateConverter extends PatientBaseConverter<PatientCreateRequest, Patient> {

    @Override
    public Patient convert(PatientCreateRequest request) {

        Patient patient = new Patient();

        patient.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        patient.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(patient, request);
    }
}
