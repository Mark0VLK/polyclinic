package com.volkonovskij.service.impl;

import com.volkonovskij.repository.RolesRepository;
import com.volkonovskij.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository repository;

    @Override
    public List<String> userRoles(Long id) {

        long parsedId;

        try {
            parsedId = Long.parseLong(String.valueOf(id));
        } catch (NumberFormatException e) {
            parsedId = 0L;
        }
        return repository.userRoles(parsedId);
    }
}
