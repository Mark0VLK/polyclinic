package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByIsDeletedIsFalse();

    void deleteDoctorById();
}
