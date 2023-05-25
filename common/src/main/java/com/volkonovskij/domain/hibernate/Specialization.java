package com.volkonovskij.domain.hibernate;

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
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "doctors"
})
@ToString(exclude = {
        "doctors"
})
@Builder
@Entity
@Cacheable("specializations")
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(generator = "specializations_id_seq", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "specializations_id_seq", sequenceName = "specializations_id_seq", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Doctor> doctors = Collections.emptySet();
}
