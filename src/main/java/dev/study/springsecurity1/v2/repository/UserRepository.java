package dev.study.springsecurity1.v2.repository;

import dev.study.springsecurity1.v2.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
}
