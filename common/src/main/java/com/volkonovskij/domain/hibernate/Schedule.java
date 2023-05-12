package com.volkonovskij.domain.hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    private Long id;

    @Column
    private Timestamp date;

    @Column(name = "time_start")
    private Timestamp timeStart;

    @Column(name = "time_finish")
    private Timestamp timeFinish;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
