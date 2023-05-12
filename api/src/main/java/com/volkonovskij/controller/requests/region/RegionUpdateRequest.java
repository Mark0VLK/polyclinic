package com.volkonovskij.controller.requests.region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class RegionUpdateRequest extends RegionCreateRequest {

    private Long id;
}
