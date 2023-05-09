package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.UserCreateRequest;
import com.volkonovskij.controller.requests.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.AuthenticationInfo;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.service.HibernateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
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
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {

        if (result.hasErrors()){
            throw new IllegalRequestException(result);
        }

        HibernateUser hibernateUser = HibernateUser.builder()
                .login(request.getLogin())
                .phoneNumber(request.getPhoneNumber())
                .passportSeriesAndNumber(request.getPassportSeriesAndNumber())
                .build();

        hibernateUser.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        hibernateUser.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        AuthenticationInfo info =  new AuthenticationInfo(request.getPassword(), request.getEmail());
        hibernateUser.setAuthenticationInfo(info);

        hibernateUser = userService.create(hibernateUser);
        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(Principal principal, @RequestBody UserUpdateRequest request) {

        HibernateUser one = userService.findById(request.getId());

        one.setId(request.getId());
        one.setLogin(request.getLogin());
        one.setPhoneNumber(request.getPhoneNumber());
        one.setPassportSeriesAndNumber(request.getPassportSeriesAndNumber());

        one = userService.update(one);
        return new ResponseEntity<>(one, HttpStatus.CREATED);
    }
}
