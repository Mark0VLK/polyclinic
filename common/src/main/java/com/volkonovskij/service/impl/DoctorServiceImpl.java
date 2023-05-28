package com.volkonovskij.service.impl;

import com.volkonovskij.repository.DoctorsRepository;
import com.volkonovskij.service.DoctorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorsService {

    private final DoctorsRepository repository;

    @Override
    public List<Object[]> DoctorsBySpecialization(String specialization) {

        if (specialization.length() < 3 || specialization.length() > 30)
            throw new NumberFormatException("The name of the specialty does not match the validation!");

        return repository.DoctorsBySpecialization(specialization.toLowerCase());
    }
}
