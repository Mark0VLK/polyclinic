package com.volkonovskij.controller.converter.patient;

import com.volkonovskij.controller.requests.patient.PatientCreateRequest;
import com.volkonovskij.domain.Patient;
import com.volkonovskij.domain.Region;
import com.volkonovskij.domain.User;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.RegionsRepository;
import com.volkonovskij.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientCreateConverter extends PatientBaseConverter<PatientCreateRequest, Patient> {

    private final UsersRepository usersRepository;

    private final RegionsRepository regionsRepository;

    @Override
    public Patient convert(PatientCreateRequest request) {

        Patient patient = new Patient();

        patient.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        patient.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<User> user = usersRepository.findById(request.getUserId());
        patient.setUser(user.orElseThrow(EntityNotFoundException::new));

        Optional<Region> region = regionsRepository.findById(request.getRegionNumber());
        patient.setRegionNumber(region.orElseThrow(EntityNotFoundException::new));

        return doConvert(patient, request);
    }
}
