package com.noirix.repository.hibernate.impl;

import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.repository.hibernate.HibernateRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateRoleRepositoryImpl implements HibernateRoleRepository {

    private final EntityManagerFactory entityManagerFactory;
    @Override
    public HibernateRole findById(Long id) {
        return null;
    }

    @Override
    public List<HibernateRole> findAll() {
        final String findAllHQL = "select l from HibernateRole l";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateRole.class).getResultList();
    }

    @Override
    public HibernateRole create(HibernateRole object) {
        return null;
    }

    @Override
    public HibernateRole update(Long id, HibernateRole object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public HibernateRole update(HibernateRole object) {
        return null;
    }
}
