package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateRegion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegionsDataRepository extends JpaRepository<HibernateRegion, Long> {

    @Cacheable("regions")
    List<HibernateRegion> findByIsDeletedIsFalse();
}
