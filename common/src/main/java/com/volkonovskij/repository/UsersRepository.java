package com.volkonovskij.repository;

import com.volkonovskij.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findByIsDeletedIsFalse();

    List<User> findByLogin(String login);

    Optional<User> findByAuthenticationInfoEmail(String email);
}
