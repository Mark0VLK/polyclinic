package com.volkonovskij.repository.hibernate;

import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.repository.CRUDRepository;

import java.util.List;
import java.util.Optional;

public interface HibernateUserRepository extends CRUDRepository<Long, HibernateUser> {
    HibernateUser update(HibernateUser object);

    List<HibernateUser> searchUser(String query, Double weight);

    Optional<HibernateUser> findByEmail(String email);
}
