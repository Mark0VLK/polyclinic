package com.volkonovskij.controller.converter.visit;

import com.volkonovskij.controller.requests.visit.VisitUpdateRequest;
import com.volkonovskij.domain.hibernate.Visit;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.VisitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VisitUpdateConverter extends VisitBaseConverter<VisitUpdateRequest, Visit> {

    private final VisitsRepository repository;

    @Override
    public Visit convert(VisitUpdateRequest source) {

        Optional<Visit> visit = repository.findById(source.getId());
        return doConvert(visit.orElseThrow(EntityNotFoundException::new), source);
    }
}