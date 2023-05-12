package com.volkonovskij.controller.converter.specialization;

import com.volkonovskij.controller.requests.specialization.SpecializationUpdateRequest;
import com.volkonovskij.domain.hibernate.Specialization;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.SpecializationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SpecializationUpdateConverter extends SpecializationBaseConverter<SpecializationUpdateRequest, Specialization> {

    private final SpecializationsRepository repository;

    @Override
    public Specialization convert(SpecializationUpdateRequest source) {

        Optional<Specialization> patient = repository.findById(source.getId());
        return doConvert(patient.orElseThrow(EntityNotFoundException::new), source);
    }
}
