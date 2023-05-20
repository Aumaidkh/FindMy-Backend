package com.findmy.repository;

import com.findmy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    public User findByPhone(String phone);
}
