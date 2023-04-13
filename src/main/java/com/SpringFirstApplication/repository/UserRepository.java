package com.SpringFirstApplication.repository;

import com.SpringFirstApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username,String email);

     Boolean  existsByEmail(String email);
     Boolean existsByUsername(String username);
}
