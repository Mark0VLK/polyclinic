package com.volkonovskij.service;
import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    User create(User object);

    User update(Long id, User object);

    void delete(Long id);

    List<User> changedOverTime(int number_of_days);
    Map<String, String> emailAndPhoneNumber();
    List<Role> getUserAuthorities(Long userId);
    Optional<User> findByEmail(String email);
}