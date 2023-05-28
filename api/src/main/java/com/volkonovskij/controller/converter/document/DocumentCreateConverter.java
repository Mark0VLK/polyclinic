package com.volkonovskij.controller.converter.document;

import com.volkonovskij.controller.requests.document.DocumentCreateRequest;
import com.volkonovskij.domain.Document;
import com.volkonovskij.domain.User;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DocumentCreateConverter extends DocumentBaseConverter<DocumentCreateRequest, Document> {

    private final UsersRepository usersRepository;

    @Override
    public Document convert(DocumentCreateRequest request) {

        Document document = new Document();

        document.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        document.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        Optional<User> user = usersRepository.findById(request.getUserId());
        document.setUser(user.orElseThrow(EntityNotFoundException::new));

        return doConvert(document, request);
    }
}
