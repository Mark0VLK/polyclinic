package com.volkonovskij.controller.converter.region;

import com.volkonovskij.controller.requests.region.RegionCreateRequest;
import com.volkonovskij.domain.Region;
import org.springframework.core.convert.converter.Converter;

public abstract class RegionBaseConverter<S, T> implements Converter<S, T> {

    public Region doConvert(Region regionForUpdate, RegionCreateRequest request) {

        regionForUpdate.setNumber(request.getNumber());
        regionForUpdate.setAddressRange(request.getAddressRange());

        return regionForUpdate;
    }
}
