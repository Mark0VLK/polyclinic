package com.volkonovskij.controller.converter.user;

import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.domain.User;
import org.springframework.core.convert.converter.Converter;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public User doConvert(User userForUpdate, UserCreateRequest request) {

        userForUpdate.setLogin(request.getLogin());
        userForUpdate.setPhoneNumber(request.getPhoneNumber());

        return userForUpdate;
    }
}
