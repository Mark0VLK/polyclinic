package com.volkonovskij.repository;

import com.volkonovskij.domain.Role;
import com.volkonovskij.domain.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends CRUDRepository <Long, User> {

    List<User> changedOverTime(int numberOfDays);
    Map<String, String> emailAndPhoneNumber();
    List<Role> getUserAuthorities(Long userId);
    Optional<User> findByEmail(String email);
}