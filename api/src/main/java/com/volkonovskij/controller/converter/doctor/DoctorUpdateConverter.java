package com.volkonovskij.controller.converter.doctor;

import com.volkonovskij.controller.requests.doctor.DoctorUpdateRequest;
import com.volkonovskij.domain.hibernate.Doctor;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.DoctorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorUpdateConverter extends DoctorBaseConverter<DoctorUpdateRequest, Doctor> {

    private final DoctorsRepository repository;

    @Override
    public Doctor convert(DoctorUpdateRequest request) {

        Optional<Doctor> doctor = repository.findById(request.getId());
        doctor.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(doctor.orElseThrow(EntityNotFoundException::new), request);
    }
}
