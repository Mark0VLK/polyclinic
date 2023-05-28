package com.volkonovskij.controller.converter.visit;

import com.volkonovskij.controller.requests.visit.VisitCreateRequest;
import com.volkonovskij.domain.Doctor;
import com.volkonovskij.domain.Patient;
import com.volkonovskij.domain.Visit;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.DoctorsRepository;
import com.volkonovskij.repository.PatientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VisitCreateConverter extends VisitBaseConverter<VisitCreateRequest, Visit> {

    private final PatientsRepository patientsRepository;

    private final DoctorsRepository doctorsRepository;

    @Override
    public Visit convert(VisitCreateRequest request) {

        Visit visit = new Visit();

        visit.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        visit.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<Patient> patient = patientsRepository.findById(request.getPatientCardNumber());
        visit.setPatient(patient.orElseThrow(EntityNotFoundException::new));

        Optional<Doctor> doctor = doctorsRepository.findById(request.getDoctorId());
        visit.setDoctor(doctor.orElseThrow(EntityNotFoundException::new));

        return doConvert(visit, request);
    }
}
