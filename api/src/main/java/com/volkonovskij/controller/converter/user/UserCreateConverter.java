package com.volkonovskij.controller.converter.user;

import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.domain.hibernate.AuthenticationInfo;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.security.config.JWTConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, HibernateUser> {

    private final JWTConfiguration configuration;

    private final PasswordEncoder encoder;

    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser hibernateUser = new HibernateUser();

        hibernateUser.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        hibernateUser.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        String resultPassword = request.getPassword() + configuration.getServerPasswordSalt();
        String encode = encoder.encode(resultPassword);

        AuthenticationInfo info =  new AuthenticationInfo(encode, request.getEmail());
        hibernateUser.setAuthenticationInfo(info);

        return doConvert(hibernateUser, request);
    }
}
