package com.volkonovskij.controller.converter.role;

import com.volkonovskij.controller.requests.role.RoleCreateRequest;
import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.User;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleCreateConverter extends RoleBaseConverter<RoleCreateRequest, Role> {

    private final UsersRepository usersRepository;

    @Override
    public Role convert(RoleCreateRequest request) {

        Role role = new Role();

        role.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        role.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<User> user = usersRepository.findById(request.getUserId());
        role.setUser(user.orElseThrow(EntityNotFoundException::new));

        return doConvert(role, request);
    }
}
