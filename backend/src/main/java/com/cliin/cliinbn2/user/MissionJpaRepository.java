package com.cliin.cliinbn2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MissionJpaRepository extends JpaRepository<Mission, String> {

    Optional<Mission> findById(String Id);
}
