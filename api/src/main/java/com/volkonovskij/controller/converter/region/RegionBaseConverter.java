package com.volkonovskij.controller.converter.region;

import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.domain.hibernate.HibernateRegion;
import org.springframework.core.convert.converter.Converter;

public abstract class RegionBaseConverter<S, T> implements Converter<S, T> {

    public HibernateRegion doConvert(HibernateRegion regionForUpdate, RegionCreateRequest request) {

        regionForUpdate.setNumber(request.getNumber());
        regionForUpdate.setAddressRange(request.getAddressRange());

        return regionForUpdate;
    }
}
