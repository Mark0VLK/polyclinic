package com.volkonovskij.controller.requests.schedules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SchedulesUpdateRequest extends SchedulesCreateRequest {

    private Long id;
}
