package com.volkonovskij.controller.converter.user;

import com.volkonovskij.controller.requests.user.UserUpdateRequest;
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
public class UserUpdateConverter extends UserBaseConverter<UserUpdateRequest, User> {

    private final UsersRepository repository;

    @Override
    public User convert(UserUpdateRequest request) {

        Optional<User> user = repository.findById(request.getId());
        user.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(user.orElseThrow(EntityNotFoundException::new), request);
    }
}
