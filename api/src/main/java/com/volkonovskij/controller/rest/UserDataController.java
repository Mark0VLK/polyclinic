package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.user.UserCreateRequest;
import com.volkonovskij.controller.requests.user.UserUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateUser;
import com.volkonovskij.repository.springdata.UserDataRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/springdata/users")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataRepository userRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all users",
            description = "Find All Users without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HibernateUser.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<HibernateUser> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data User Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                userRepository.findAll(PageRequest.of(page,1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find a user",
            description = "Find the user by his id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HibernateUser.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<HibernateUser> user = userRepository.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for all active users",
            description = "Search for all active users",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Users",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleUsers() {

        Map<String, List<HibernateUser>> users = Collections.singletonMap("result", userRepository.findByIsDeletedIsFalse());

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PostMapping
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<Object> saveUser(@Valid @RequestBody UserCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernateUser hibernateUser = conversionService.convert(request, HibernateUser.class);

        hibernateUser = userRepository.save(hibernateUser);

        return new ResponseEntity<>(hibernateUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserUpdateRequest request) {

        HibernateUser hibernateUser = conversionService.convert(request, HibernateUser.class);

        hibernateUser = userRepository.save(hibernateUser);

        return new ResponseEntity<>(hibernateUser, HttpStatus.OK);
    }

    @GetMapping("/find/{login}")
    public ResponseEntity<Object> findByLogin(@PathVariable String login) {

        List<HibernateUser> result = userRepository.findByLogin(login);

        return new ResponseEntity<>(Collections.singletonMap("result", result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {

        Optional<HibernateUser> user = userRepository.findById(id);

        userRepository.deleteById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}