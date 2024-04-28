package com.sherwin.dev.springsecurity.repository;

import com.sherwin.dev.springsecurity.entity.Role;
import com.sherwin.dev.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
