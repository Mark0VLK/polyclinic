package com.volkonovskij.controller.converter.role;

import com.volkonovskij.controller.requests.role.RoleCreateRequest;
import com.volkonovskij.domain.Role;
import org.springframework.core.convert.converter.Converter;

public abstract class RoleBaseConverter<S, T> implements Converter<S, T> {

    public Role doConvert(Role roleForUpdate, RoleCreateRequest request) {

        roleForUpdate.setRoleName(request.getRoleName());

        return roleForUpdate;
    }
}
