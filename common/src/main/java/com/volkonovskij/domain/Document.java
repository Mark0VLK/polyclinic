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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "user"
})
@ToString(exclude = {
        "user"
})
@Entity
@Cacheable("personal_doc")
@Table(name = "personal_doc")
public class Document {

    @Id
    @GeneratedValue(generator = "personal_doc_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "personal_doc_id_seq", sequenceName = "personal_doc_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "passport_series")
    private String passportSeries;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "identification_no")
    private String identificationNo;

    @Column
    private Timestamp created;

    @Column
    private Timestamp changed;

    @Column(name = "expiration_date")
    private Timestamp expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
