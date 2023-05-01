package com.noirix.service.impl;

import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.repository.hibernate.HibernateRoleRepository;
import com.noirix.service.HibernateRoleService;
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
