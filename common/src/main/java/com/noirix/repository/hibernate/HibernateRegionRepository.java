package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateRegion;
import com.noirix.repository.CRUDRepository;

public interface HibernateRegionRepository extends CRUDRepository<Long, HibernateRegion> {

    HibernateRegion update(HibernateRegion object);
}
