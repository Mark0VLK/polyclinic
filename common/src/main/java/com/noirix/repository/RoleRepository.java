package com.noirix.repository;

import com.noirix.domain.Role;

import java.util.List;


public interface RoleRepository extends CRUDRepository<Long, Role> {

    List<Role> getUserAuthorities(Long userId);
}
