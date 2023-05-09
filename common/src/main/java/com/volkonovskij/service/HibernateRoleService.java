package com.volkonovskij.service;

import com.volkonovskij.domain.hibernate.HibernateRole;
import java.util.List;

public interface HibernateRoleService {

    HibernateRole findById(Long id);

    List<HibernateRole> findAll();

    HibernateRole create(HibernateRole object);

    HibernateRole update(HibernateRole object);

    void delete(Long id);
}
