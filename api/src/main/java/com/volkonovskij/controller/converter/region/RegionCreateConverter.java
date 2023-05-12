package com.volkonovskij.controller.converter.region;

import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.domain.hibernate.HibernateRegion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RegionCreateConverter extends RegionBaseConverter<RegionCreateRequest, HibernateRegion> {

    @Override
    public HibernateRegion convert(RegionCreateRequest request) {

        HibernateRegion region = new HibernateRegion();

        region.setCreated(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(region, request);
    }
}
