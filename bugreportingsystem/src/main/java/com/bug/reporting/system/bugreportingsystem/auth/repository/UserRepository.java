package com.bug.reporting.system.bugreportingsystem.auth.repository;


import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByForgetPasswordCode(String code);

    List<User> findByForgetPasswordCodeTimestampBefore(Timestamp twoMinutesAgo);
}

