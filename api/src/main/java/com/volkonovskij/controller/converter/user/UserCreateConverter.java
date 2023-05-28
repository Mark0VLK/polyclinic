package com.volkonovskij.controller.converter.user;

import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.domain.AuthenticationInfo;
import com.volkonovskij.domain.User;
import com.volkonovskij.security.config.JWTConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserCreateConverter extends UserBaseConverter<UserCreateRequest, User> {

    private final JWTConfiguration configuration;

    private final PasswordEncoder encoder;

    @Override
    public User convert(UserCreateRequest request) {

        User user = new User();

        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        String resultPassword = request.getPassword() + configuration.getServerPasswordSalt();
        String encode = encoder.encode(resultPassword);

        AuthenticationInfo info = new AuthenticationInfo(encode, request.getEmail());
        user.setAuthenticationInfo(info);

        return doConvert(user, request);
    }
}
