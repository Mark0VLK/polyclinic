package com.volkonovskij.service;

import com.volkonovskij.domain.Patient;
import com.volkonovskij.domain.system.Gender;

import java.sql.Timestamp;
import java.util.List;

public interface PatientsService {

    List<Object[]> phoneAndAddressByInitials(String name, String surname);

    List<Object[]> medicalHistory(String name, String surname);

    List<Patient> findByGenderAndBirthDate(Gender gender, Timestamp birthDate);

    Float unpaidAmount(String name, String surname);
}
