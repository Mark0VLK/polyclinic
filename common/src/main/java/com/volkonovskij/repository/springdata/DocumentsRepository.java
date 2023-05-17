package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepository extends JpaRepository<HibernateDocument, Long> {

}
