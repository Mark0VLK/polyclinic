package com.volkonovskij.controller.converter.user;

import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public HibernateUser doConvert(HibernateUser userForUpdate, UserCreateRequest request) {

        userForUpdate.setLogin(request.getLogin());
        userForUpdate.setPhoneNumber(request.getPhoneNumber());
        userForUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return userForUpdate;
    }
}
