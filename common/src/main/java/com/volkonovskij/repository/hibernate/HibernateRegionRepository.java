package com.volkonovskij.repository.hibernate;

import com.volkonovskij.domain.hibernate.HibernateRegion;
import com.volkonovskij.repository.CRUDRepository;

public interface HibernateRegionRepository extends CRUDRepository<Long, HibernateRegion> {

    HibernateRegion update(HibernateRegion object);
}
