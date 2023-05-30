package com.volkonovskij.repository;

import com.volkonovskij.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface PatientsRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByIsDeletedIsFalse();

    @Query(value = "select u.phone_number, p.address from users as u inner join patients p on u.id = p.id_user where " +
            "lower(p.name) = :name and lower(p.surname) = :surname and p.is_deleted is false and u.is_deleted is false",
            nativeQuery = true
    )
    List<Object[]> phoneAndAddressByInitials(String name, String surname);

    @Query(value = "select d.surname, v.note, v.changed from patients as p inner join visits v on p.card_number = v.patient_card_number " +
            "join doctors d on d.id = v.id_doctor where v.status is true " +
            "and p.is_deleted is false and lower(p.name) =:name and lower(p.surname) =:surname",
            nativeQuery = true
    )
    List<Object[]> medicalHistory(String name, String surname);

    @Query(value = "select p.card_number, p.name, p.surname, p.birthday_data, p.address " +
            "from patients as p inner join regions r on p.region_number = r.id " +
            "where p.birthday_data >:data and r.number =:regionNumber and p.is_deleted is false",
            nativeQuery = true)
    List<Object[]> findByGenderAndRegionNumber(Timestamp data, Long regionNumber);

    @Query(value = "select sum(v.price) from patients as p inner join visits v on p.card_number = v.patient_card_number " +
            "where v.status is false and p.is_deleted is false and lower(p.name) =:name and lower(p.surname) =:surname",
            nativeQuery = true)
    Float unpaidAmount(String name, String surname);
}
