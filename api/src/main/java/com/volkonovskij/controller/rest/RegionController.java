package com.volkonovskij.controller.rest;

import com.volkonovskij.domain.hibernate.HibernateRegion;
import com.volkonovskij.repository.hibernate.HibernateRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/regions")
@RequiredArgsConstructor
public class RegionController {

    private final HibernateRegionRepository regionRepository;
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateRegion> users = regionRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
