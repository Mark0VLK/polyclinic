package com.volkonovskij.controller.converter.schedules;

import com.volkonovskij.controller.requests.schedules.SchedulesCreateRequest;
import com.volkonovskij.domain.hibernate.Schedule;
import org.springframework.core.convert.converter.Converter;

public abstract class SchedulesBaseConverter<S, T> implements Converter<S, T> {

    public Schedule doConvert(Schedule scheduleForUpdate, SchedulesCreateRequest request) {

        scheduleForUpdate.setDate(request.getDate());
        scheduleForUpdate.setTimeStart(request.getTimeStart());
        scheduleForUpdate.setTimeFinish(request.getTimeFinish());

        return scheduleForUpdate;
    }
}
