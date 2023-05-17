package com.volkonovskij.controller.rest;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.User;
import com.volkonovskij.domain.hibernate.HibernateRole;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.repository.springdata.RolesRepository;
import com.volkonovskij.service.RoleService;
import com.volkonovskij.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    private final RolesRepository repository;

    private final ConversionService conversionService;

    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        List<HibernateRole> roles = repository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUsersAuthorities(@PathVariable Long userId) {

        User user = userService.findById(userId);
        List<Role> roles = roleService.getUserAuthorities(userId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("user", user);
        result.put("roles", roles);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
