package com.volkonovskij.security.controller;

import com.volkonovskij.security.config.JWTConfiguration;
import com.volkonovskij.security.dto.AuthRequest;
import com.volkonovskij.security.dto.AuthResponse;
import com.volkonovskij.security.jwt.TokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider provider;

    private final UserDetailsService userProvider;

    private final JWTConfiguration configuration;

    @Operation(
            summary = "Authorization on the site",
            description = "authorization on the site",
            parameters = {
                    @Parameter(
                            name = "login",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "renata_test15@gmail.com",
                                    type = "string",
                                    description = "user email")
                    ),
                    @Parameter(
                            name = "password",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark10mark10",
                                    type = "string",
                                    description = "user password")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Authorization was successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthRequest.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@Parameter(hidden = true) @Valid @ModelAttribute AuthRequest request) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword() + configuration.getServerPasswordSalt()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(
                AuthResponse
                        .builder()
                        .login(request.getLogin())
                        .token(
                                provider.generateToken(
                                        userProvider.loadUserByUsername(request.getLogin())
                                )
                        )
                        .build()
        );
    }

}
