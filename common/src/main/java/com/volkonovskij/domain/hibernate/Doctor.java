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
@Table(name = "doctors")
public class Doctor {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String office;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_specialization")
    private Long idSpecialization;

    @Column(name = "id_schedule")
    private Long idSchedule;

    @Column(name = "id_region")
    private Long idRegion;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
