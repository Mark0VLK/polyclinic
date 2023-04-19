package com.noirix.domain.hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class HibernateUser {

    @Id
    private Long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String email;
    @Column(name = "passport_series_and_number")
    private String passportSeriesAndNumber;
    @Column
    private Timestamp created;
    @Column
    private Timestamp changed;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public HibernateUser(Long id, String login, String password, String phoneNumber, String email,
                         String passportSeriesAndNumber) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportSeriesAndNumber = passportSeriesAndNumber;
    }
    public HibernateUser(String login, String password, String phoneNumber, String email,
                String passportSeriesAndNumber) {
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passportSeriesAndNumber = passportSeriesAndNumber;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
