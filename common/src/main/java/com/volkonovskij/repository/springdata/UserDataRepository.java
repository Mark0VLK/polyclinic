package com.volkonovskij.repository.springdata;

import com.volkonovskij.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<HibernateUser, Long> {

}
