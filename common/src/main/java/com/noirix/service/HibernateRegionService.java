package com.noirix.service;

import com.noirix.domain.hibernate.HibernateRegion;
import java.util.List;

public interface HibernateRegionService {

    HibernateRegion findById(Long id);

    List<HibernateRegion> findAll();

    HibernateRegion create(HibernateRegion object);

    HibernateRegion update(HibernateRegion object);

    void delete(Long id);
}
