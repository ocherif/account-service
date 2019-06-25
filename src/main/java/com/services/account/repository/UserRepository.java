package com.services.account.repository;

import com.services.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(" select u from User u  where u.username = ?1")

    Optional<User> findUserWithName(String username);
}