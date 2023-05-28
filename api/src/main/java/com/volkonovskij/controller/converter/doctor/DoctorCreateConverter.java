package com.volkonovskij.controller.converter.doctor;

import com.volkonovskij.controller.requests.doctor.DoctorCreateRequest;
import com.volkonovskij.domain.Doctor;
import com.volkonovskij.domain.Region;
import com.volkonovskij.domain.Specialization;
import com.volkonovskij.domain.User;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.RegionsRepository;
import com.volkonovskij.repository.SpecializationsRepository;
import com.volkonovskij.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DoctorCreateConverter extends DoctorBaseConverter<DoctorCreateRequest, Doctor> {

    private final UsersRepository usersRepository;

    private final SpecializationsRepository specializationsRepository;

    private final RegionsRepository regionsRepository;

    @Override
    public Doctor convert(DoctorCreateRequest request) {

        Doctor doctor = new Doctor();

        doctor.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        doctor.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<User> user = usersRepository.findById(request.getUserId());
        doctor.setUser(user.orElseThrow(EntityNotFoundException::new));

        Optional<Specialization> specialization = specializationsRepository.findById(request.getSpecializationId());
        doctor.setSpecialization(specialization.orElseThrow(EntityNotFoundException::new));

        Optional<Region> region = regionsRepository.findById(request.getRegionNumber());
        doctor.setRegionNumber(region.orElseThrow(EntityNotFoundException::new));

        return doConvert(doctor, request);
    }
}
