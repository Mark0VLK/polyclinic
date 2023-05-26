package com.volkonovskij.controller.requests.user;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Object with user information on registration stage")
public class UserCreateRequest {

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "Mark_MV",
        description = "user login"
    )
    @Size(min = 2, max = 20)
    @NotNull
    private String login;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "12345_qwerty",
        description = "user password"
    )
    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "+375298881200",
        description = "user phone number"
    )
    @NotNull
    @Size(min = 9, max = 14)
    private String phoneNumber;

    @Schema
    (
        requiredMode = Schema.RequiredMode.REQUIRED,
        type = "String",
        example = "markvolkonovskij@gmail.com",
        description = "user email"
    )
    @NotNull
    @Size(min = 3, max = 60)
    private String email;
}
