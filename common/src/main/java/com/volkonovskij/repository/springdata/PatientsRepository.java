package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernatePatient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientsRepository extends JpaRepository<HibernatePatient, Long> {

    List<HibernatePatient> findByIsDeletedIsFalse();

}
