package com.volkonovskij.controller.converter.visit;

import com.volkonovskij.controller.requests.visit.VisitCreateRequest;
import com.volkonovskij.domain.hibernate.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class VisitCreateConverter extends VisitBaseConverter<VisitCreateRequest, Visit> {

    @Override
    public Visit convert(VisitCreateRequest request) {

        Visit visit = new Visit();

        visit.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        visit.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(visit, request);
    }
}
