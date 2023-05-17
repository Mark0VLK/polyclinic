package com.volkonovskij.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "doctor", "patient"
})
@ToString(exclude = {
        "doctor", "patient"
})
@Builder
@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(generator = "visits_id_seq", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "visits_id_seq", sequenceName = "visits_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private Boolean status = false;

    @Column
    private String note;

    @Column
    private Float price;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "patient_card_number")
    @JsonBackReference
    private HibernatePatient patient;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    @JsonBackReference
    private Doctor doctor;
}
