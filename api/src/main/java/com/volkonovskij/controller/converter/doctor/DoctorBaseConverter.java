package com.volkonovskij.controller.converter.doctor;

import com.volkonovskij.controller.requests.doctor.DoctorCreateRequest;
import com.volkonovskij.domain.Doctor;
import org.springframework.core.convert.converter.Converter;

public abstract class DoctorBaseConverter<S, T> implements Converter<S, T> {
    public Doctor doConvert(Doctor doctorForUpdate, DoctorCreateRequest request) {

        doctorForUpdate.setName(request.getName());
        doctorForUpdate.setSurname(request.getSurname());
        doctorForUpdate.setOffice(request.getOffice());

        return doctorForUpdate;
    }
}
