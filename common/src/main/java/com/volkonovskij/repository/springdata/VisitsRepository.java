package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsRepository extends JpaRepository<Visit,Long> {

}
