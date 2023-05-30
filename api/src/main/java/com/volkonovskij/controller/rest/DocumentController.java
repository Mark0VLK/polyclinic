package com.volkonovskij.controller.rest;

import com.volkonovskij.controller.exceptions.IllegalRequestException;
import com.volkonovskij.controller.requests.document.DocumentCreateRequest;
import com.volkonovskij.controller.requests.document.DocumentUpdateRequest;
import com.volkonovskij.domain.Document;
import com.volkonovskij.repository.DocumentsRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            description = "Find all documents without limitations",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded documents",
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
            summary = "Search for documents with output to the page",
            description = "load page by number with sort and offset params",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded documents",
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
            description = "find the document by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded document",
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
            description = "search for all documents that have not expired",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully loaded active documents",
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

    @Operation(
            summary = "Create a new document",
            description = "create a new document",
            parameters = {
                    @Parameter(
                            name = "passportSeries",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "KH",
                                    type = "string",
                                    description = "passport series")
                    ),
                    @Parameter(
                            name = "passportNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "1237654",
                                    type = "string",
                                    description = "passport number")
                    ),
                    @Parameter(
                            name = "identificationNo",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "7637905A001PB6",
                                    type = "string",
                                    description = "passport identification number")
                    ),
                    @Parameter(
                            name = "expirationDate",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "passport expiration date")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "id of the user who owns this document")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "CREATED",
                            description = "Successfully created document",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Document.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Object> saveDocument(@Parameter(hidden = true) @Valid @ModelAttribute DocumentCreateRequest request, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalRequestException(result);
        }

        Document document = conversionService.convert(request, Document.class);

        document = documentsRepository.save(document);

        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Edit an existing document",
            description = "edit an existing document",
            parameters = {
                    @Parameter(
                            name = "id",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "18",
                                    type = "number",
                                    description = "document id")
                    ),
                    @Parameter(
                            name = "passportSeries",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "KH",
                                    type = "string",
                                    description = "passport series")
                    ),
                    @Parameter(
                            name = "passportNumber",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "1237654",
                                    type = "string",
                                    description = "passport number")
                    ),
                    @Parameter(
                            name = "identificationNo",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "7637905A001PB6",
                                    type = "string",
                                    description = "passport identification number")
                    ),
                    @Parameter(
                            name = "expirationDate",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "2023-05-30 00:00:00",
                                    type = "string", format = "date-time",
                                    description = "passport expiration date")
                    ),
                    @Parameter(
                            name = "userId",
                            required = true,
                            in = ParameterIn.QUERY,
                            schema = @Schema(
                                    requiredMode = Schema.RequiredMode.REQUIRED,
                                    example = "4",
                                    type = "number",
                                    description = "id of the user who owns this document")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "Successfully changed the document information",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Document.class)
                            )
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Object> updateDocument(@Parameter(hidden = true) @Valid @ModelAttribute DocumentUpdateRequest request) {

        Document document = conversionService.convert(request, Document.class);

        document = documentsRepository.save(document);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete a document by id",
            description = "delete a document by id",
            responses = {
                    @ApiResponse(
                            responseCode = "OK",
                            description = "The document has been successfully removed",
                            content = @Content(
                                    mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDocument(@Parameter(name = "id", example = "2", required = true) @PathVariable Long id) {

        Optional<Document> document = documentsRepository.findById(id);

        documentsRepository.deleteById(id);

        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
