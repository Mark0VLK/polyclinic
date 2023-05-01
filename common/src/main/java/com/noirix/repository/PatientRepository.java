package com.noirix.repository;

import com.noirix.domain.dto.PatientDTO;
import com.noirix.domain.Patient;

public interface PatientRepository extends CRUDRepository <Long, Patient> {
    PatientDTO getNameAndSurnameByPhone(String phoneNumber);
    PatientDTO  getAddressBySurname(String surname);
}
