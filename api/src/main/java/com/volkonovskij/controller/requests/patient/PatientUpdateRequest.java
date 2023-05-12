package com.volkonovskij.controller.requests.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PatientUpdateRequest extends PatientCreateRequest {

    private Long cardNumber;
}
