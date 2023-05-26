package com.volkonovskij.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.volkonovskij.domain.system.Gender;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
        "user", "regionNumber"
})
@ToString(exclude = {
        "user", "regionNumber"
})
@Builder
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @Column(name = "card_number")
    @GeneratedValue(generator = "patients_card_number_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "patients_card_number_seq", sequenceName = "patients_card_number_seq", allocationSize = 1, initialValue = 10)
    private Long cardNumber;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NOT_SELECTED;

    @Column(name = "birthday_data")
    private Timestamp birthDate;

    @Column
    private String address;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "region_number")
    @JsonBackReference
    private Region regionNumber;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Visit> visits = Collections.emptySet();
}
