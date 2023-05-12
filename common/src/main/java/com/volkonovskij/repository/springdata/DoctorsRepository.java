package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {

}
