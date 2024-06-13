package com.hexaware.eventmanagement.repository;
 
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.eventmanagement.entity.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);
    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
 
    Optional<User> findByEmail(String email);
	Boolean existsByUsername(String username);
}