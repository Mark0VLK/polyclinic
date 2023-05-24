package com.volkonovskij.controller.converter.patient;

import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.domain.hibernate.HibernatePatient;
import org.springframework.core.convert.converter.Converter;

public abstract class PatientBaseConverter<S, T> implements Converter<S, T> {

    public HibernatePatient doConvert(HibernatePatient patientForUpdate, PatientCreateRequest request) {

        patientForUpdate.setName(request.getName());
        patientForUpdate.setSurname(request.getSurname());
        patientForUpdate.setGender(request.getGender());
        patientForUpdate.setBirthDate(request.getBirthDate());
        patientForUpdate.setAddress(request.getAddress());

        return patientForUpdate;
    }
}

