package com.volkonovskij.repository;

import com.volkonovskij.domain.Region;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionsRepository extends JpaRepository<Region, Long> {

    @Cacheable("regions")
    List<Region> findByIsDeletedIsFalse();
}
