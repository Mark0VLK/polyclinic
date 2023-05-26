package com.volkonovskij.repository;

import com.volkonovskij.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByIsDeletedIsFalse();
}
