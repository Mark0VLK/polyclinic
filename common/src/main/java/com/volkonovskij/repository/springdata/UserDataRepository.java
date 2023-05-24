package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDataRepository extends JpaRepository<HibernateUser, Long> {

    List<HibernateUser> findByLogin(String login);
}
