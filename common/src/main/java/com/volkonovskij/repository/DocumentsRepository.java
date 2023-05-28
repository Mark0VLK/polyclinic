package com.volkonovskij.repository;

import com.volkonovskij.domain.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface DocumentsRepository extends JpaRepository<Document, Long> {

    @Cacheable("personal_doc")
    @Query(value = "select u from Document u where u.expirationDate > :currentTimestamp ORDER BY u.id")
    List<Document> findAllValidDocuments(Timestamp currentTimestamp);

}
