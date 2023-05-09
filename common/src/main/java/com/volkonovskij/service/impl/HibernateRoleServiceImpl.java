package com.volkonovskij.service.impl;

import com.volkonovskij.domain.hibernate.HibernateRole;
import com.volkonovskij.repository.hibernate.HibernateRoleRepository;
import com.volkonovskij.service.HibernateRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateRoleServiceImpl implements HibernateRoleService {

    private final HibernateRoleRepository hibernateRoleRepository;

    @Override
    public HibernateRole findById(Long id) {
        return hibernateRoleRepository.findById(id);
    }

    @Override
    public List<HibernateRole> findAll() {
        return hibernateRoleRepository.findAll();
    }

    @Override
    public HibernateRole create(HibernateRole object) {
        return hibernateRoleRepository.create(object);
    }

    @Override
    public HibernateRole update(HibernateRole object) {
        return hibernateRoleRepository.update(object);
    }

    @Override
    public void delete(Long id) {

    }
}
