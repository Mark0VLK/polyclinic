package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<HibernateRole, Long> {

}
