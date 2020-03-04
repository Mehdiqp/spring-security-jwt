package com.company.security.jwt.repository;

import com.company.security.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);


    boolean deleteByUsername(String username);
}
