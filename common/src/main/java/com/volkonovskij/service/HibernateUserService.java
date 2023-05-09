package com.volkonovskij.service;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.hibernate.HibernateUser;

import java.util.List;
import java.util.Optional;

public interface HibernateUserService {

    HibernateUser findById(Long id);

    List<HibernateUser> findAll();

    HibernateUser create(HibernateUser object);

    HibernateUser update(HibernateUser object);

    void delete(Long id);

    List<HibernateUser> search(String query, Double weight);

    List<Role> getHibernateUserAuthorities(Long userId);

    Optional<HibernateUser> findByEmail(String email);
}
