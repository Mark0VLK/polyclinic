package com.volkonovskij.repository;

import com.volkonovskij.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {

}
