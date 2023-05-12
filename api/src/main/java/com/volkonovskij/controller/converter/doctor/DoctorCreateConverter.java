package com.volkonovskij.controller.converter.doctor;

import com.volkonovskij.controller.requests.doctor.DoctorCreateRequest;
import com.volkonovskij.domain.hibernate.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DoctorCreateConverter extends DoctorBaseConverter<DoctorCreateRequest, Doctor> {

    @Override
    public Doctor convert(DoctorCreateRequest request) {

        Doctor doctor = new Doctor();

        doctor.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        doctor.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(doctor, request);
    }
}
