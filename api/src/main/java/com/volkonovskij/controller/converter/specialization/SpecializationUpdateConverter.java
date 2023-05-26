package com.volkonovskij.controller.converter.specialization;

import com.volkonovskij.controller.requests.specialization.SpecializationUpdateRequest;
import com.volkonovskij.domain.Specialization;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.SpecializationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SpecializationUpdateConverter extends SpecializationBaseConverter<SpecializationUpdateRequest, Specialization> {

    private final SpecializationsRepository repository;

    @Override
    public Specialization convert(SpecializationUpdateRequest source) {

        Optional<Specialization> specialization = repository.findById(source.getId());
        specialization.orElseThrow(EntityNotFoundException::new).setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return doConvert(specialization.orElseThrow(EntityNotFoundException::new), source);
    }
}
