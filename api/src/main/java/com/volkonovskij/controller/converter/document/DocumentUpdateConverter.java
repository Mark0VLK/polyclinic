package com.volkonovskij.controller.converter.document;

import com.volkonovskij.controller.requests.document.DocumentUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateDocument;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.DocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DocumentUpdateConverter extends DocumentBaseConverter<DocumentUpdateRequest, HibernateDocument> {

    private final DocumentsRepository repository;

    @Override
    public HibernateDocument convert(DocumentUpdateRequest request) {

        Optional<HibernateDocument> document = repository.findById(request.getId());
        document.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(document.orElseThrow(EntityNotFoundException::new), request);
    }
}
