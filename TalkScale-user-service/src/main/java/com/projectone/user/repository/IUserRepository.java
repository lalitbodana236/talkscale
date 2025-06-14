package com.projectone.user.repository;

import com.projectone.user.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<AppUser, Long> {
    
    Optional<AppUser> findByEmail(String email);
}
