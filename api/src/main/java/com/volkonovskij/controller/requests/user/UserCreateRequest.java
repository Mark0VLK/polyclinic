package com.volkonovskij.controller.requests.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class UserCreateRequest {

    @Size(min = 2, max =10)
    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    @NotNull
    private String passportSeriesAndNumber;
}
