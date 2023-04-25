package com.noirix.service.impl;

import com.noirix.domain.hibernate.HibernateRegion;
import com.noirix.repository.hibernate.HibernateRegionRepository;
import com.noirix.service.HibernateRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateRegionServiceImpl implements HibernateRegionService {

    private final HibernateRegionRepository hibernateRegionRepository;

    @Override
    public HibernateRegion findById(Long id) {
        return hibernateRegionRepository.findById(id);
    }

    @Override
    public List<HibernateRegion> findAll() {
        return hibernateRegionRepository.findAll();
    }

    @Override
    public HibernateRegion create(HibernateRegion object) {
        return hibernateRegionRepository.create(object);
    }

    @Override
    public HibernateRegion update(HibernateRegion object) {
        return hibernateRegionRepository.update(object);
    }

    @Override
    public void delete(Long id) {
    }
}
