package com.volkonovskij.repository;

import com.volkonovskij.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitsRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByIsDeletedIsFalse();

}
