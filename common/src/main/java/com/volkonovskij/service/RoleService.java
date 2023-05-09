package com.volkonovskij.service;

import com.volkonovskij.domain.Role;
import java.util.List;

public interface RoleService {

    Role findById(Long id);

    List<Role> findAll();

    Role create(Role object);

    Role update(Long id, Role object);

    void delete(Long id);
    List<Role> getUserAuthorities(Long userId);
}
