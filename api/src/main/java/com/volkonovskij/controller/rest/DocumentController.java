package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.document.DocumentCreateRequest;
import com.volkonovskij.controller.requests.document.DocumentUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateDocument;
import com.volkonovskij.repository.springdata.DocumentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/springdata/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentsRepository documentsRepository;

    private final ConversionService conversionService;
    @GetMapping
    public ResponseEntity<Object> getAllDocuments() {
        List<HibernateDocument> documents = documentsRepository.findAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveDocument(@Valid @RequestBody DocumentCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        HibernateDocument document = conversionService.convert(request, HibernateDocument.class);

        document = documentsRepository.save(document);

        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateDocument(@RequestBody DocumentUpdateRequest request) {

        HibernateDocument document = conversionService.convert(request, HibernateDocument.class);

        document = documentsRepository.save(document);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
