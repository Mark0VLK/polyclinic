package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.role.RoleCreateRequest;
import com.volkonovskij.controller.requests.role.RoleUpdateRequest;
import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.system.SystemRoles;
import com.volkonovskij.repository.RolesRepository;
import com.volkonovskij.service.RolesService;
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
import java.util.Optional;

@RestController
@RequestMapping("/rest/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RolesRepository rolesRepository;

    private final RolesService rolesService;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all roles",
            description = "find all roles without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded roles",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        List<Role> roles = rolesRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Operation(
            summary = "Search for roles with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded roles",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                rolesRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the role",
            description = "identify the role by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded role",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Role> role = rolesRepository.findById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Operation(
            summary = "Find all user roles",
            description = "find all user roles by their id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class))
                    )
            }
    )
    @GetMapping("user/{id}")
    public ResponseEntity<Object> AllUserRolesById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        List<String> roles = rolesService.userRoles(id);

        return new ResponseEntity<>(Collections.singletonMap("roles", roles), HttpStatus.OK);
    }

    @Operation(
            summary = "Create a new role",
            description = "create a new role",
            parameters = {
                    @Parameter(
                            name = "roleName",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "ROLE_USER",
                                    type = "SystemRoles",
                                    implementation = SystemRoles.class,
                                    description = "name of the role in the system")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "15",
                                    type = "number",
                                    description = "id of the user to whom this role belongs")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created role",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> saveRole(@Parameter(hidden = true) @Valid @ModelAttribute RoleCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Role role = conversionService.convert(request, Role.class);

        role = rolesRepository.save(role);

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing role",
            description = "edit an existing role",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "1",
                                    type = "number",
                                    description = "role id")
                    ),
                    @Parameter(
                            name = "roleName",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "ROLE_USER",
                                    type = "SystemRoles",
                                    implementation = SystemRoles.class,
                                    description = "name of the role in the system")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "15",
                                    type = "number",
                                    description = "id of the user to whom this role belongs")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the role information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Role.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateRole(@Parameter(hidden = true) @Valid @ModelAttribute RoleUpdateRequest request) {

        Role role = conversionService.convert(request, Role.class);

        role = rolesRepository.save(role);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a role by id",
            description = "delete a role by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The role has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRole(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Role> role = rolesRepository.findById(id);

        rolesRepository.deleteById(id);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
