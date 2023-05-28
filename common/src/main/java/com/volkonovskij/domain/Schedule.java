package com.volkonovskij.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {
        "doctor"
})
@ToString(exclude = {
        "doctor"
})
@Setter
@Getter
@Entity
@Cacheable("schedules")
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(generator = "schedules_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "schedules_id_seq", sequenceName = "schedules_id_seq", allocationSize = 1, initialValue = 10)
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

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    @JsonBackReference
    private Doctor doctor;
}
