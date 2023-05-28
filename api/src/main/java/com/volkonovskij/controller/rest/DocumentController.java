package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.document.DocumentCreateRequest;
import com.volkonovskij.controller.requests.document.DocumentUpdateRequest;
import com.volkonovskij.domain.Document;
import com.volkonovskij.repository.DocumentsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentsRepository documentsRepository;

    private final ConversionService conversionService;

    @Operation(
            summary = "Find all documents",
            description = "Find All Documents without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Documents",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Document.class))
                    )
            }
    )
    @GetMapping
    public ResponseEntity<Object> getAllDocuments() {
        List<Document> documents = documentsRepository.findAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @Operation(
            summary = "Spring Data Document Search with Pageable Params",
            description = "Load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Document",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/page/{page}")
    public ResponseEntity<Object> page(@Parameter(name = "page", example = "1", required = true) @PathVariable int page) {

        return new ResponseEntity<>(Collections.singletonMap("result",
                documentsRepository.findAll(PageRequest.of(page, 1))), HttpStatus.OK);
    }

    @Operation(
            summary = "Find the document",
            description = "Find the document by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded Documents",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Document.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getDocumentById(@Parameter(name = "id", example = "1", required = true) @PathVariable Long id) {

        Optional<Document> document = documentsRepository.findById(id);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @Operation(
            summary = "Search through all active documents",
            description = "Search for all documents that have not expired",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active Documents",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PageImpl.class))
                    )
            }
    )
    @GetMapping("/active")
    public ResponseEntity<Object> findAllVisibleDocuments() {

        Map<String, List<Document>> documents = Collections.singletonMap("result", documentsRepository.findAllValidDocuments(Timestamp.valueOf(LocalDateTime.now())));

        return new ResponseEntity<>(documents, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Object> saveDocument(@Valid @RequestBody DocumentCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Document document = conversionService.convert(request, Document.class);

        document = documentsRepository.save(document);

        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateDocument(@RequestBody DocumentUpdateRequest request) {

        Document document = conversionService.convert(request, Document.class);

        document = documentsRepository.save(document);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDocument(@PathVariable Long id) {

        Optional<Document> document = documentsRepository.findById(id);

        documentsRepository.deleteById(id);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
