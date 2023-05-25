package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VisitsRepository extends JpaRepository<Visit,Long> {

    List<Visit> findByIsDeletedIsFalse();

}
