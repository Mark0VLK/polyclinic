package com.noirix.repository.hibernate.impl;

import com.noirix.domain.hibernate.HibernateRegion;
import com.noirix.repository.hibernate.HibernateRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateRegionRepositoryImpl implements HibernateRegionRepository {

    private final EntityManagerFactory entityManagerFactory;
    @Override
    public HibernateRegion findById(Long id) {
        return null;
    }

    @Override
    public List<HibernateRegion> findAll() {

        final String findAllHQL = "select r from HibernateRegion r";
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateRegion.class).getResultList();
    }

    @Override
    public HibernateRegion create(HibernateRegion object) {
        return null;
    }

    @Override
    public HibernateRegion update(Long id, HibernateRegion object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public HibernateRegion update(HibernateRegion object) {
        return null;
    }
}
