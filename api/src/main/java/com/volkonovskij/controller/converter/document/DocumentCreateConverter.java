package com.volkonovskij.controller.converter.document;

import com.volkonovskij.controller.requests.document.DocumentCreateRequest;
import com.volkonovskij.domain.hibernate.HibernateDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DocumentCreateConverter extends DocumentBaseConverter<DocumentCreateRequest, HibernateDocument>  {

    @Override
    public HibernateDocument convert(DocumentCreateRequest request) {

        HibernateDocument document = new HibernateDocument();

        document.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        document.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(document, request);
    }
}
