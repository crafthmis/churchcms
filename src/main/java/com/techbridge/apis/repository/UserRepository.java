package com.techbridge.apis.repository;

import com.techbridge.apis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
