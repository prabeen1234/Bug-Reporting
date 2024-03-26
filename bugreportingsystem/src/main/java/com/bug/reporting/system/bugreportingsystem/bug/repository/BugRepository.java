package com.bug.reporting.system.bugreportingsystem.bug.repository;


import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BugRepository extends JpaRepository<Bug, Integer> {
    Optional<Bug> findByUser(Optional<User> optionalUser);

}
