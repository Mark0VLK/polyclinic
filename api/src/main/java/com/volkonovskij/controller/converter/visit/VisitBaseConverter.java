package com.volkonovskij.controller.converter.visit;

import com.volkonovskij.controller.requests.visit.VisitCreateRequest;
import com.volkonovskij.domain.hibernate.Visit;
import org.springframework.core.convert.converter.Converter;

public abstract class VisitBaseConverter<S, T> implements Converter<S, T> {

    public Visit doConvert(Visit visitForUpdate, VisitCreateRequest request) {

        visitForUpdate.setStatus(request.getStatus());
        visitForUpdate.setNote(request.getNote());
        visitForUpdate.setPrice(request.getPrice());

        return visitForUpdate;
    }
}
