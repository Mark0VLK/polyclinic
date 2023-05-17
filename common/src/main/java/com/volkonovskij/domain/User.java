package com.volkonovskij.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    private Long id;
    private String login;
    private String password;
    private String phoneNumber;
    private String email;
    private Timestamp created;
    private Timestamp changed;

    public User(Long id, String login, String password, String phoneNumber, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public User(String login, String password, String phoneNumber, String email) {
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
