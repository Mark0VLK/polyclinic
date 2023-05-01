package com.noirix.repository.hibernate;

import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.repository.CRUDRepository;

public interface HibernateRoleRepository extends CRUDRepository<Long, HibernateRole> {

    HibernateRole update(HibernateRole object);

}
