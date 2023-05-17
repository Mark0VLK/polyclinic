package com.volkonovskij.controller.requests.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class DocumentUpdateRequest extends DocumentCreateRequest {

    private Long id;
}
