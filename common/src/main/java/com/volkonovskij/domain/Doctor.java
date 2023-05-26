package com.volkonovskij.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "specialization", "schedules", "regionNumber", "user", "visits"
})
@ToString(exclude = {
        "specialization", "schedules", "regionNumber", "user", "visits"
})
@Builder
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(generator = "doctors_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "doctors_id_seq", sequenceName = "doctors_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String office;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "id_specialization")
    @JsonBackReference
    private Specialization specialization;

    @ManyToMany(mappedBy = "doctors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("doctors")
    private Set<Schedule> schedules = Collections.emptySet();

    @ManyToOne
    @JoinColumn(name = "region_number")
    @JsonBackReference
    private Region regionNumber;

    @OneToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Visit> visits = Collections.emptySet();
}
