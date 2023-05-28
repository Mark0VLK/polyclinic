package com.volkonovskij.controller.requests.role;

import com.volkonovskij.domain.system.SystemRoles;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
@Schema(description = "Object with information about the role at the registration stage")
public class RoleCreateRequest {

    @Schema
            (
                    requiredMode = Schema.RequiredMode.REQUIRED,
                    type = "SystemRoles",
                    example = "ROLE_OTHER",
                    description = "user role"
            )
    @NotNull
    private SystemRoles roleName;

    @NotNull
    private Long userId;
}
