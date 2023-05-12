package com.volkonovskij.controller.requests.region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Validated
public class RegionCreateRequest {

    @NotNull
    private Long number;

    @NotNull
    private String addressRange;
}
