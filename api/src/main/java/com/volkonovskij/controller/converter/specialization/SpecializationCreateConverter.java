package com.volkonovskij.controller.converter.specialization;

import com.volkonovskij.controller.requests.specialization.SpecializationCreateRequest;
import com.volkonovskij.domain.Specialization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SpecializationCreateConverter extends SpecializationBaseConverter<SpecializationCreateRequest, Specialization> {

    @Override
    public Specialization convert(SpecializationCreateRequest request) {

        Specialization specialization = new Specialization();

        specialization.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        specialization.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(specialization, request);
    }
}
