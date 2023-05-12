package com.volkonovskij.controller.converter.doctor;

import com.volkonovskij.controller.requests.doctor.DoctorUpdateRequest;
import com.volkonovskij.domain.hibernate.Doctor;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.DoctorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorUpdateConverter extends DoctorBaseConverter<DoctorUpdateRequest, Doctor> {

    private final DoctorsRepository repository;

    @Override
    public Doctor convert(DoctorUpdateRequest source) {

        Optional<Doctor> doctor = repository.findById(source.getId());
        return doConvert(doctor.orElseThrow(EntityNotFoundException::new), source);
    }
}
