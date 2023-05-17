package com.volkonovskij.controller.converter.specialization;

import com.volkonovskij.controller.requests.specialization.SpecializationCreateRequest;
import com.volkonovskij.domain.hibernate.Specialization;
import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class SpecializationBaseConverter<S, T> implements Converter<S, T> {

    public Specialization doConvert(Specialization specializationForUpdate, SpecializationCreateRequest request) {

        specializationForUpdate.setName(request.getName());
        specializationForUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return specializationForUpdate;
    }
}