package com.volkonovskij.controller.converter.region;

import com.volkonovskij.controller.requests.region.RegionUpdateRequest;
import com.volkonovskij.domain.Region;
import com.volkonovskij.exception.EntityNotFoundException;
import com.volkonovskij.repository.RegionsDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegionUpdateConverter extends RegionBaseConverter<RegionUpdateRequest, Region> {

    private final RegionsDataRepository repository;

    @Override
    public Region convert(RegionUpdateRequest request) {

        Optional<Region> region = repository.findById(request.getId());
        return doConvert(region.orElseThrow(EntityNotFoundException::new), request);
    }
}
