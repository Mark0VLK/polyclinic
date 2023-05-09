package com.volkonovskij.service;

import com.volkonovskij.domain.hibernate.HibernateRegion;
import java.util.List;

public interface HibernateRegionService {

    HibernateRegion findById(Long id);

    List<HibernateRegion> findAll();

    HibernateRegion create(HibernateRegion object);

    HibernateRegion update(HibernateRegion object);

    void delete(Long id);
}
