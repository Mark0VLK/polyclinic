package com.volkonovskij.controller.requests.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RoleUpdateRequest extends RoleCreateRequest {

    private Long id;
}
