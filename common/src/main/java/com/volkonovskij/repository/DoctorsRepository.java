package com.volkonovskij.repository;

import com.volkonovskij.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByIsDeletedIsFalse();

    @Query(value = "select d.surname, s.date, s.time_start, s.time_finish from doctors as d inner join specializations p " +
            "on p.id = d.id_specialization join schedules s on d.id = s.id_doctor where lower(p.name) =:specialization",
            nativeQuery = true)
    List<Object[]> DoctorsBySpecialization(String specialization);
}
