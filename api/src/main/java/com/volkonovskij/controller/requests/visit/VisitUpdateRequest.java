package com.volkonovskij.controller.requests.visit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class VisitUpdateRequest extends VisitCreateRequest {

    private Long id;
}
