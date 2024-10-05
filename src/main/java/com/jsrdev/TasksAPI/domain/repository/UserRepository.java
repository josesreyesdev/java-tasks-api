package com.jsrdev.TasksAPI.domain.repository;

import com.jsrdev.TasksAPI.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

    Page<User> findByActiveTrue(Pageable pageable);
}
