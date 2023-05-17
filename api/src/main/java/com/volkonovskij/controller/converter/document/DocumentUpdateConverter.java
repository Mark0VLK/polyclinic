package com.volkonovskij.controller.converter.document;

import com.volkonovskij.controller.requests.document.DocumentUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateDocument;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.DocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DocumentUpdateConverter extends DocumentBaseConverter<DocumentUpdateRequest, HibernateDocument> {

    private final DocumentsRepository repository;

    @Override
    public HibernateDocument convert(DocumentUpdateRequest source) {

        Optional<HibernateDocument> document = repository.findById(source.getId());
        return doConvert(document.orElseThrow(EntityNotFoundException::new), source);
    }
}
