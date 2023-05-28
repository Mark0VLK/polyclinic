package com.volkonovskij.service.impl;

import com.volkonovskij.domain.Patient;
import com.volkonovskij.domain.system.Gender;
import com.volkonovskij.repository.PatientsRepository;
import com.volkonovskij.service.PatientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientsService {

    private final PatientsRepository repository;

    @Override
    public List<Object[]> phoneAndAddressByInitials(String name, String surname) {
        return repository.phoneAndAddressByInitials(name.toLowerCase(), surname.toLowerCase());
    }

    @Override
    public List<Object[]> medicalHistory(String name, String surname) {
        return repository.medicalHistory(name.toLowerCase(), surname.toLowerCase());
    }

    @Override
    public List<Patient> findByGenderAndBirthDate(Gender gender, Timestamp birthDate) {
        return repository.findByGenderAndBirthDate(gender, birthDate);
    }

    @Override
    public Float unpaidAmount(String name, String surname) {

        return repository.unpaidAmount(name.toLowerCase(), surname.toLowerCase());
    }
}
