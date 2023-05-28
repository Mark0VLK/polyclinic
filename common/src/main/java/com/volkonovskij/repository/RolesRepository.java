package com.volkonovskij.repository;

import com.volkonovskij.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r.role_name from users as u inner join roles r on r.user_id = u.id where u.id =:id",
            nativeQuery = true)
    List<String> userRoles(Long id);
}
