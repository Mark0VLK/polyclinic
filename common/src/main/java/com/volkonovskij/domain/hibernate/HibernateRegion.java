package com.volkonovskij.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "users"
})
@ToString(exclude = {
        "users"
})
@Setter
@Getter
@Builder
@Entity
@Cacheable("regions")
@Table(name = "regions")
public class HibernateRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Long number;

    @Column(name = "address_range")
    private String addressRange;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToMany
    @JoinTable(name = "patients",
            joinColumns = @JoinColumn(name = "region_number"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
    @JsonIgnoreProperties("regions")
    private Set<HibernateUser> users = Collections.emptySet();
}
