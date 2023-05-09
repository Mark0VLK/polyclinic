package com.volkonovskij.service.impl;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.repository.hibernate.HibernateUserRepository;
import com.volkonovskij.service.HibernateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HibernateUserServiceImpl implements HibernateUserService {

    private final HibernateUserRepository hibernateUserRepository;

    @Override
    public HibernateUser findById(Long id) {
        return hibernateUserRepository.findById(id);
    }

    @Override
    public List<HibernateUser> findAll() {
        return hibernateUserRepository.findAll();
    }

    @Override
    public HibernateUser create(HibernateUser object) {
        return hibernateUserRepository.create(object);
    }

    @Override
    public HibernateUser update(HibernateUser object) {
        return hibernateUserRepository.update(object);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<HibernateUser> search(String query, Double weight) {
        return null;
    }

    @Override
    public List<Role> getHibernateUserAuthorities(Long userId) {
        return null;
    }

    @Override
    public Optional<HibernateUser> findByEmail(String email) {
        return Optional.empty();
    }
}
