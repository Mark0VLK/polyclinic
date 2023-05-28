package com.volkonovskij.controller.converter.schedule;

import com.volkonovskij.controller.requests.schedule.ScheduleCreateRequest;
import com.volkonovskij.domain.Schedule;
import org.springframework.core.convert.converter.Converter;

public abstract class ScheduleBaseConverter<S, T> implements Converter<S, T> {

    public Schedule doConvert(Schedule scheduleForUpdate, ScheduleCreateRequest request) {

        scheduleForUpdate.setDate(request.getDate());
        scheduleForUpdate.setTimeStart(request.getTimeStart());
        scheduleForUpdate.setTimeFinish(request.getTimeFinish());

        return scheduleForUpdate;
    }
}
