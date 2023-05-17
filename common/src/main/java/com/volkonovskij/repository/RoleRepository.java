package com.volkonovskij.repository;

import com.volkonovskij.domain.Role;
import java.util.List;

public interface RoleRepository extends CRUDRepository<Long, Role> {

    List<Role> getUserAuthorities(Long userId);
}
