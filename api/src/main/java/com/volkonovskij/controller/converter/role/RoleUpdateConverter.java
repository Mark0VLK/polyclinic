package com.volkonovskij.controller.converter.role;

import com.volkonovskij.controller.requests.role.RoleUpdateRequest;
import com.volkonovskij.domain.Role;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleUpdateConverter extends RoleBaseConverter<RoleUpdateRequest, Role> {

    private final RolesRepository repository;

    @Override
    public Role convert(RoleUpdateRequest request) {

        Optional<Role> role = repository.findById(request.getId());
        role.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(role.orElseThrow(EntityNotFoundException::new), request);
    }
}
