package com.volkonovskij.controller.converter.region;

import com.volkonovskij.controller.requests.region.RegionUpdateRequest;
import com.volkonovskij.domain.hibernate.HibernateRegion;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.springdata.RegionsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegionUpdateConverter extends RegionBaseConverter<RegionUpdateRequest, HibernateRegion> {

    private final RegionsDataRepository repository;

    @Override
    public HibernateRegion convert(RegionUpdateRequest request) {

        Optional<HibernateRegion> region = repository.findById(request.getId());
        return doConvert(region.orElseThrow(EntityNotFoundException::new), request);
    }
}
