package com.volkonovskij.service;

import java.util.List;

public interface DoctorsService {

    List<Object[]> DoctorsBySpecialization(String specialization);
}
