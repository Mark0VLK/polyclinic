package com.volkonovskij.controller.converter.region;

import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.domain.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RegionCreateConverter extends RegionBaseConverter<RegionCreateRequest, Region> {

    @Override
    public Region convert(RegionCreateRequest request) {

        Region region = new Region();

        region.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(region, request);
    }
}
