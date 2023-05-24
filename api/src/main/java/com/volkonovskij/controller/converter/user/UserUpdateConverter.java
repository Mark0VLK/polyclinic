package com.volkonovskij.controller.converter.user;

import com.volkonovskij.controller.requests.user.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter extends UserBaseConverter<UserUpdateRequest, HibernateUser> {

        private final UserDataRepository repository;

        @Override
        public HibernateUser convert(UserUpdateRequest request) {

            Optional<HibernateUser> user = repository.findById(request.getId());
            user.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
            return doConvert(user.orElseThrow(EntityNotFoundException::new), request);
        }
}
