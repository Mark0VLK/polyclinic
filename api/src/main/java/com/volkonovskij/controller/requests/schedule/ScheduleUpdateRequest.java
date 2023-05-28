package com.volkonovskij.controller.requests.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleUpdateRequest extends ScheduleCreateRequest {

    private Long id;
}
