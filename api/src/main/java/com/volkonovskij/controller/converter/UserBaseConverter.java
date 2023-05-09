package com.volkonovskij.controller.converter;

import com.volkonovskij.controller.requests.UserCreateRequest;
import com.volkonovskij.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

public abstract class UserBaseConverter<S, T> implements Converter<S, T> {

    public HibernateUser doConvert(HibernateUser userForUpdate, UserCreateRequest request) {

        userForUpdate.setLogin(request.getLogin());
        userForUpdate.setPhoneNumber(request.getPhoneNumber());
        userForUpdate.setPassportSeriesAndNumber(request.getPassportSeriesAndNumber());

        return userForUpdate;
    }
}
