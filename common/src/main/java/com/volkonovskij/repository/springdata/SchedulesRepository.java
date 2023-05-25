package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Schedule;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {

    @Cacheable("schedules")
    List<Schedule> findByIsDeletedIsFalse();
}
