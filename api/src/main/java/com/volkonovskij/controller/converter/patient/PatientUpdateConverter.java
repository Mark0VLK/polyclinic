package com.volkonovskij.controller.converter.patient;

import com.volkonovskij.controller.requests.patient.PatientUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernatePatient;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientUpdateConverter extends PatientBaseConverter<PatientUpdateRequest, HibernatePatient> {

    private final PatientsRepository repository;

    @Override
    public HibernatePatient convert(PatientUpdateRequest source) {

        Optional<HibernatePatient> patient = repository.findById(source.getCardNumber());
        return doConvert(patient.orElseThrow(EntityNotFoundException::new), source);
    }
}
