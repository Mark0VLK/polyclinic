package com.volkonovskij.service.impl;

import com.volkonovskij.domain.Role;
import com.volkonovskij.repository.RoleRepository;
import com.volkonovskij.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long id) {
        /*Validation layer*/
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> findAll() {
         /*Validation layer*/
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role object) {
         /*Validation layer*/
        return roleRepository.create(object);
    }

    @Override
    public Role update(Long id, Role object) {
         /*Validation layer*/
        return roleRepository.update(id, object);
    }

    @Override
    public void delete(Long id) {
         /*Validation layer*/
        roleRepository.delete(id);
    }

    @Override
    public List<Role> getUserAuthorities(Long userId) {
        /*Validation layer*/
        return roleRepository.getUserAuthorities(userId);
    }
}
