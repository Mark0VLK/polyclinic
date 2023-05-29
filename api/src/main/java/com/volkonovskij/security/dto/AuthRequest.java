package com.volkonovskij.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
@Schema(description = "Object with user credentials at the authentication stage")
public class AuthRequest {

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "renata_test15@gmail.com",
            description = "user email"
    )
    @Size(min = 2, max = 20)
    @NotNull
    private String login;

    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "String",
            example = "Mark10mark10",
            description = "user password"
    )
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
    @Size(min = 8, max = 20)
    @NotNull
    private String password;
}
