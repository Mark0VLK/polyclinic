package com.noirix.repository.springdata;

import com.noirix.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends
        JpaRepository<HibernateUser, Long> {

}
