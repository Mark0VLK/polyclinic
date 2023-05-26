package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends JpaRepository<HibernateUser, Long> {

    List<HibernateUser> findByIsDeletedIsFalse();

    List<HibernateUser> findByLogin(String login);

    Optional<HibernateUser> findByAuthenticationInfoEmail(String email);
}
