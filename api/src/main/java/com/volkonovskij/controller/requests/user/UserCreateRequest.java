package com.volkonovskij.controller.requests.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with user information on registration stage")
public class UserCreateRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Mark_MV",
            description = "user login"
    )
    @Size(min = 2, max = 20)
    @NotNull
    private String login;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Qwerty12345",
            description = "user password"
    )
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    @Size(min = 8, max = 20)
    @NotNull
    private String password;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "+375298882142",
            description = "user phone number"
    )
    @Pattern(regexp = "^\\+\\d{9,14}")
    @NotNull
    private String phoneNumber;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "markvolkonovskij@gmail.com",
            description = "user email"
    )
    @Pattern(regexp = ".*@.*")
    @Size(min = 3, max = 60)
    @NotNull
    private String email;
}
