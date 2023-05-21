package com.volkonovskij.repository.rowmapper;

import com.volkonovskij.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.volkonovskij.repository.columns.UserColumns.CHANGED;
import static com.volkonovskij.repository.columns.UserColumns.CREATED;
import static com.volkonovskij.repository.columns.UserColumns.EMAIL;
import static com.volkonovskij.repository.columns.UserColumns.ID;
import static com.volkonovskij.repository.columns.UserColumns.LOGIN;
import static com.volkonovskij.repository.columns.UserColumns.PASSWORD;
import static com.volkonovskij.repository.columns.UserColumns.PHONE_NUMBER;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user;

        try {
            user = User.builder()
                    .id(rs.getLong(ID))
                    .login(rs.getString(LOGIN))
                    .password(rs.getString(PASSWORD))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .email(rs.getString(EMAIL))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
