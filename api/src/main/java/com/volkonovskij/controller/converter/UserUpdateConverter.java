package com.volkonovskij.controller.converter;

import com.volkonovskij.controller.requests.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateConverter extends
        UserBaseConverter<UserUpdateRequest, HibernateUser> {

        private final UserDataRepository repository;

        @Override
        public HibernateUser convert(UserUpdateRequest source) {

            Optional<HibernateUser> user = repository.findById(source.getId());
            return doConvert(user.orElseThrow(EntityNotFoundException::new), source);
        }
}
