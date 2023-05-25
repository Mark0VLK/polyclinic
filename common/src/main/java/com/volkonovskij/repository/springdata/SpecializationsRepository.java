package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Specialization;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpecializationsRepository extends JpaRepository<Specialization, Long> {

    @Cacheable("specializations")
    List<Specialization> findByIsDeletedIsFalse();

}
