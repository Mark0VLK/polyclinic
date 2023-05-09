package com.volkonovskij.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {
        "regions", "roles"
})
@ToString(exclude = {
        "regions", "roles"
})
@Setter
@Getter
@Entity
@Table(name = "users")
public class HibernateUser {

    @Id
    @GeneratedValue(generator = "users_id_seq", strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1, initialValue = 100)
    private Long id;

    @Column
    private String login;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "password", column = @Column(name = "password"))
    })
    private AuthenticationInfo authenticationInfo;

    @Column(name = "passport_series_and_number")
    private String passportSeriesAndNumber;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private Set<HibernateRegion> regions = Collections.emptySet();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private Set<HibernateRole> roles = Collections.emptySet();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
    @JsonManagedReference
    private HibernatePersonalDocument document;

    public HibernateUser(Long id, String login, String password, String phoneNumber, String email,
                         String passportSeriesAndNumber) {
        this.id = id;
        this.login = login;
//        this.password = password;
        this.phoneNumber = phoneNumber;
//        this.email = email;
        this.passportSeriesAndNumber = passportSeriesAndNumber;
    }
    public HibernateUser(String login, String password, String phoneNumber, String email,
                String passportSeriesAndNumber) {
        this.login = login;
//        this.password = password;
        this.phoneNumber = phoneNumber;
//        this.email = email;
        this.passportSeriesAndNumber = passportSeriesAndNumber;
    }
}
