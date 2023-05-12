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
@Table(name = "visits")
public class Visit {

    @Id
    private Long id;

    @Column
    private Boolean status = false;

    @Column
    private String note;

    @Column
    private Float price;

    @Column(name = "patient_card_number")
    private Long patientCardNumber;

    @Column(name = "id_appointment")
    private Long idAppointment;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
