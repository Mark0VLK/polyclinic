package com.volkonovskij.service.impl;

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
    public List<Object[]> findByGenderAndRegionNumber(Timestamp data, Long regionNumber) {
        return repository.findByGenderAndRegionNumber(data, regionNumber);
    }

    @Override
    public Float unpaidAmount(String name, String surname) {

        return repository.unpaidAmount(name.toLowerCase(), surname.toLowerCase());
    }
}
