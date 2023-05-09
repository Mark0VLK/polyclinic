package com.volkonovskij.repository.rowmapper;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.SystemRoles;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.volkonovskij.repository.columns.RoleColumns.ROLE_NAME;
import static com.volkonovskij.repository.columns.RoleColumns.USER_ID;
import static com.volkonovskij.repository.columns.RoleColumns.ID;
import static com.volkonovskij.repository.columns.RoleColumns.CREATED;
import static com.volkonovskij.repository.columns.RoleColumns.CHANGED;

@Component
public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        Role role;

        try {
            role = Role.builder()
                    .id(rs.getLong(ID))
                    .systemRole(SystemRoles.valueOf(rs.getString(ROLE_NAME)))
                    .userId(rs.getLong(USER_ID))
                    .created(rs.getTimestamp(CREATED))
                    .changed(rs.getTimestamp(CHANGED))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }
}
