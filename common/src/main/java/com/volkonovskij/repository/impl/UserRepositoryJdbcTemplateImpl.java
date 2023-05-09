package com.volkonovskij.repository.impl;

import com.volkonovskij.constans.Milliseconds;
import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.User;
import com.volkonovskij.repository.UserRepository;
import com.volkonovskij.repository.rowmapper.RoleRowMapper;
import com.volkonovskij.repository.rowmapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class UserRepositoryJdbcTemplateImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;
    private final RoleRowMapper roleRowMapper;

    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject("select * from users where is_deleted = 'false' and id = " + id, userRowMapper);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users where is_deleted = 'false' order by id desc", userRowMapper);
    }

    @Override
    public User create(User user) {
        jdbcTemplate.update("insert into users(login,password, phone_number, email, passport_series_and_number, created, changed) values(?,?,?,?,?,?,?)",
                user.getLogin(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassportSeriesAndNumber(),
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()));
        return user;
    }

    @Override
    public User update(Long id, User user) {
        jdbcTemplate.update("update users set login = ?, password = ?, phone_number = ?, email = ?, " +
                "passport_series_and_number = ?, changed = ? where id = ? and is_deleted = 'false'",
                user.getLogin(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassportSeriesAndNumber(),
                Timestamp.valueOf(LocalDateTime.now()),
                id);

        return findById(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("update users set changed = ?, is_deleted = true where id = ?",
                Timestamp.valueOf(LocalDateTime.now()),
                id);
    }

    @Override
    public List<User> changedOverTime(int numberOfDays) {
        List<User> result = new ArrayList<>();
        List <User> users = findAll();
        for (User user: users) {
            long time = Timestamp.valueOf(LocalDateTime.now()).getTime() - user.getChanged().getTime();
            int days_between = (int)(time / Milliseconds.DAY);
            if (days_between <= numberOfDays)
            {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public Map<String, String> emailAndPhoneNumber() {
        List<User> users = findAll();
        Map<String, String> emailAndPhone = new HashMap<>();
        for (User user:users)
        {
            String email = user.getEmail();
            String phone = user.getPhoneNumber();
            emailAndPhone.put(email, phone);
        }
        return emailAndPhone;
    }

    @Override
    public List<Role> getUserAuthorities(Long userId) {
        return jdbcTemplate.query("select * from roles inner join users u on u.id = roles.user_id" +
                " where user_id = " + userId + " order by roles.id desc ", roleRowMapper);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = jdbcTemplate.queryForObject("select * from users where login = '" + login + "'", userRowMapper);
        return Optional.of(user);
    }
}