package com.volkonovskij.repository.hibernate;

import com.volkonovskij.domain.hibernate.HibernateRole;
import com.volkonovskij.repository.CRUDRepository;

public interface HibernateRoleRepository extends CRUDRepository<Long, HibernateRole> {

    HibernateRole update(HibernateRole object);

}
