package com.volkonovskij.controller.converter;

import com.volkonovskij.controller.requests.UserCreateRequest;
import com.volkonovskij.domain.hibernate.AuthenticationInfo;
import com.volkonovskij.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser hibernateUser = new HibernateUser();

        hibernateUser.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        hibernateUser.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        AuthenticationInfo info =  new AuthenticationInfo(request.getPassword(), request.getEmail());
        hibernateUser.setAuthenticationInfo(info);

        return doConvert(hibernateUser, request);
    }
}
