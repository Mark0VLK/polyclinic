package com.volkonovskij.controller.converter.document;

import com.volkonovskij.controller.requests.document.DocumentCreateRequest;
import com.volkonovskij.domain.Document;
import org.springframework.core.convert.converter.Converter;

public abstract class DocumentBaseConverter<S, T> implements Converter<S, T> {

    public Document doConvert(Document document, DocumentCreateRequest request) {

        document.setPassportSeries(request.getPassportSeries());
        document.setPassportNumber(request.getPassportNumber());
        document.setIdentificationNo(request.getIdentificationNo());
        document.setExpirationDate(request.getExpirationDate());

        return document;
    }
}
