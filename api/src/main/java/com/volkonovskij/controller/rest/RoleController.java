package com.volkonovskij.controller.rest;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.User;
import com.volkonovskij.domain.hibernate.HibernateRole;
import com.volkonovskij.repository.springdata.RolesRepository;
import com.volkonovskij.service.RoleService;
import com.volkonovskij.service.UserService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/springdata/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    private final UserService userService;

    private final RolesRepository rolesRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all roles",
            description = "Find All Roles without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Roles",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HibernateRole.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllRoles() {
        List<HibernateRole> roles = rolesRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data Role Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Role",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                rolesRepository.findAll(PageRequest.of(page,1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the user role",
            description = "Identify the role by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Role",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HibernateRole.class))
                    )
            }
    )
    @GetMapping("/{roleId}")
    public ResponseEntity<Object> getRoleById(@Parameter(name = "roleId", example = "1", required = true) @PathVariable Long roleId) {

        Optional<HibernateRole> role = rolesRepository.findById(roleId);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getUsersAuthorities(@PathVariable Long userId) {

        User user = userService.findById(userId);
        List<Role> roles = roleService.getUserAuthorities(userId);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("user", user);
        result.put("roles", roles);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @DeleteMapping
//    public ResponseEntity<Object> deleteRole(@RequestBody RoleUpdateRequest request) {
//
//        HibernateRole role = conversionService.convert(request, HibernateRole.class);
//
//        rolesRepository.delete(role);
//
//        return new ResponseEntity<>(role, HttpStatus.CREATED);
//    }
}
