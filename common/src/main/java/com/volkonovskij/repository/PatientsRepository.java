package com.volkonovskij.repository;

import com.volkonovskij.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientsRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByIsDeletedIsFalse();

}
