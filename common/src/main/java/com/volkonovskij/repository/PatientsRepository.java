package com.volkonovskij.repository;

import com.volkonovskij.domain.Patient;
import com.volkonovskij.domain.system.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface PatientsRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByIsDeletedIsFalse();

    @Query(value = "select u.phone_number, p.address from users as u inner join patients p on u.id = p.id_user where " +
            "lower(p.name) = :name and lower(p.surname) = :surname",
            nativeQuery = true
    )
    List<Object[]> phoneAndAddressByInitials(String name, String surname);

    @Query(value = "select d.surname, v.note, v.changed from patients as p inner join visits v on p.card_number = v.patient_card_number " +
            "join doctors d on d.id = v.id_doctor where v.status is true and lower(p.name) =:name and lower(p.surname) =:surname",
            nativeQuery = true
    )
    List<Object[]> medicalHistory(String name, String surname);

    List<Patient> findByGenderAndBirthDate(Gender gender, Timestamp birthDate);

    @Query(value = "select sum(v.price) from patients as p inner join visits v on p.card_number = v.patient_card_number " +
            "where v.status is true and lower(p.name) =:name and lower(p.surname) =:surname",
            nativeQuery = true)
    Float unpaidAmount(String name, String surname);
}
