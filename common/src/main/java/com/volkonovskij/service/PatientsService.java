package com.volkonovskij.service;

import java.sql.Timestamp;
import java.util.List;

public interface PatientsService {

    List<Object[]> phoneAndAddressByInitials(String name, String surname);

    List<Object[]> medicalHistory(String name, String surname);

    List<Object[]> findByGenderAndRegionNumber(Timestamp data, Long regionNumber);

    Float unpaidAmount(String name, String surname);
}
