package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateDocument;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.sql.Timestamp;
import java.util.List;

public interface DocumentsRepository extends JpaRepository<HibernateDocument, Long> {

    @Cacheable("personal_doc")
    @Query(value = "select u from HibernateDocument u where u.expirationDate < :currentTimestamp ORDER BY u.id")
    List<HibernateDocument> findByHQLQuery(Timestamp currentTimestamp);

}
