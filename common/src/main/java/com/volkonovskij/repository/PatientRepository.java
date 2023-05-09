package com.volkonovskij.repository;

import com.volkonovskij.domain.dto.PatientDTO;
import com.volkonovskij.domain.Patient;

public interface PatientRepository extends CRUDRepository <Long, Patient> {
    PatientDTO getNameAndSurnameByPhone(String phoneNumber);
    PatientDTO  getAddressBySurname(String surname);
}
