package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecializationsRepository extends JpaRepository<Specialization, Long> {

}
