package com.noirix.controller.rest;

import com.noirix.domain.hibernate.HibernateRole;
import com.noirix.service.HibernateRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/roles")
@RequiredArgsConstructor
public class HibernateRoleController {

    private final HibernateRoleService hibernateRoleService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateRole> roles = hibernateRoleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
