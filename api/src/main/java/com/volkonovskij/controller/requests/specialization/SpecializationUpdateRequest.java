package com.volkonovskij.controller.requests.specialization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SpecializationUpdateRequest extends SpecializationCreateRequest {

    private Long id;
}
