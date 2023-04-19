package com.noirix.repository.impl;

import com.noirix.domain.Role;
import com.noirix.repository.RoleRepository;
import com.noirix.repository.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class RoleRepositoryImpl implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RoleRowMapper roleRowMapper;

    @Override
    public Role findById(Long id) {
        return null;
    }

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query("select * from roles order by id desc",
                roleRowMapper);
    }

    @Override
    public Role create(Role object) {
        return null;
    }

    @Override
    public Role update(Long id, Role object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Role> getUserAuthorities(Long userId) {
        return null;
    }
}
