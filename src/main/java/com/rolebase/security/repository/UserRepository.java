package com.rolebase.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rolebase.security.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
}
