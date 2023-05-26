package com.volkonovskij.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "patients", "doctors"
})
@ToString(exclude = {
        "patients", "doctors"
})
@Setter
@Getter
@Builder
@Entity
@Cacheable("regions")
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(generator = "regions_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "regions_id_seq", sequenceName = "regions_id_seq", allocationSize = 1, initialValue = 10)
    private Long id;

    @Column(name = "number")
    private Long number;

    @Column(name = "address_range")
    private String addressRange;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "regionNumber", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Patient> patients = Collections.emptySet();

    @OneToMany(mappedBy = "regionNumber", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Doctor> doctors = Collections.emptySet();
}
