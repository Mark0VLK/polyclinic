package com.volkonovskij.controller.converter.patient;

import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.domain.hibernate.HibernatePatient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PatientCreateConverter extends PatientBaseConverter<PatientCreateRequest, HibernatePatient> {

    @Override
    public HibernatePatient convert(PatientCreateRequest request) {

        HibernatePatient patient = new HibernatePatient();

        patient.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        patient.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(patient, request);
    }
}
