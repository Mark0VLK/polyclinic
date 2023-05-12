package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.controller.requests.user.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.repository.springdata.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/springdata/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataRepository repository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = repository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernateUser hibernateUser = conversionService.convert(request, HibernateUser.class);

        hibernateUser = repository.save(hibernateUser);

        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateRequest request) {

        HibernateUser hibernateUser = conversionService.convert(request, HibernateUser.class);

        hibernateUser = repository.save(hibernateUser);

        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }
}
