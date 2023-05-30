package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.controller.requests.user.UserUpdateRequest;
import com.volkonovskij.domain.User;
import com.volkonovskij.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersRepository userRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all users",
            description = "find all users without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for users with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                userRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find a user",
            description = "find the user by his id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded user",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active users",
            description = "search for all active users",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleUsers() {

        Map<String, List<User>> users = Collections.singletonMap("result", userRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @Operation(
            summary = "Find a user by login",
            description = "find a user by login",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded user",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/find/{login}")
    public ResponseEntity<Object> findByLogin(@Parameter(name = "login", example = "mark_10", required = true) @PathVariable String login) {

        List<User> result = userRepository.findByLogin(login);

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @Operation(
            summary = "Create a new user",
            description = "create a new user",
            parameters = {
                    @Parameter(
                            name = "login",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark_MV",
                                    type = "string",
                                    description = "user login")
                    ),
                    @Parameter(
                            name = "password",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Qwerty12345",
                                    type = "string",
                                    description = "user password")
                    ),
                    @Parameter(
                            name = "phoneNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "+375298882142",
                                    type = "string",
                                    description = "user phone number")
                    ),
                    @Parameter(
                            name = "email",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "markvolkonovskij@gmail.com",
                                    type = "string",
                                    description = "user email")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created user",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )
                    )
            }
    )
    @PostMapping
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<Object> saveUser(@Parameter(hidden = true) @Valid @ModelAttribute UserCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        User user = conversionService.convert(request, User.class);

        user = userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing user",
            description = "Edit an existing user",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "3",
                                    type = "number",
                                    description = "user id")
                    ),
                    @Parameter(
                            name = "login",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Mark_MV",
                                    type = "string",
                                    description = "user login")
                    ),
                    @Parameter(
                            name = "password",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "Qwerty12345",
                                    type = "string",
                                    description = "user password")
                    ),
                    @Parameter(
                            name = "phoneNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "+375298882142",
                                    type = "string",
                                    description = "user phone number")
                    ),
                    @Parameter(
                            name = "email",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "markvolkonovskij@gmail.com",
                                    type = "string",
                                    description = "user email")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the user information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateUser(@Parameter(hidden = true) @Valid @ModelAttribute UserUpdateRequest request) {

        User user = conversionService.convert(request, User.class);

        user = userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a user by id",
            description = "delete a user by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The user has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);

        userRepository.deleteById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}