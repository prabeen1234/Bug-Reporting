package com.bug.reporting.system.bugreportingsystem.bug.repository;


import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Integer> {
}
