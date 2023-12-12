package com.cliin.cliinbn2.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {

    Optional<User> findByUserIdAndPassword(String userId, String password);

    Optional<User> findByUserId(String userId);
}
