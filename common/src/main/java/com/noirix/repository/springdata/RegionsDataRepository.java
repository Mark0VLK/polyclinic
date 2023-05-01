package com.noirix.repository.springdata;

import com.noirix.domain.hibernate.HibernateRegion;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionsDataRepository extends JpaRepository<HibernateRegion, Long> {

    @Cacheable("regions")
    List<HibernateRegion> findByNumber(Long number);
}