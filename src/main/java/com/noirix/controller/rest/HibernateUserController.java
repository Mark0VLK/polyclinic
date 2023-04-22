package com.noirix.controller.rest;

import com.noirix.controller.requests.UserCreateRequest;
import com.noirix.controller.requests.UserUpdateRequest;
import com.noirix.domain.Gender;
import com.noirix.domain.hibernate.HibernateUser;
import com.noirix.service.HibernateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/users")
@RequiredArgsConstructor
public class HibernateUserController {

    private final HibernateUserService userService;

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody UserCreateRequest request) {

        HibernateUser hibernateUser = HibernateUser.builder()
                .login((request.getLogin()))
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .passportSeriesAndNumber(request.getPassportSeriesAndNumber())
                .build();

        hibernateUser.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        hibernateUser.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        hibernateUser = userService.create(hibernateUser);
        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(Principal principal, @RequestBody UserUpdateRequest request) {

        HibernateUser one = userService.findById(request.getId());

        one.setId(request.getId());
        one.setLogin(request.getLogin());
        one.setPassword(request.getPassword());
        one.setPhoneNumber(request.getPhoneNumber());
        one.setEmail(request.getEmail());
        one.setPassportSeriesAndNumber(request.getPassportSeriesAndNumber());

        one = userService.update(one);
        return new ResponseEntity<>(one, HttpStatus.CREATED);
    }
}
