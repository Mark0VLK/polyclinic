package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedule, Long> {
}
