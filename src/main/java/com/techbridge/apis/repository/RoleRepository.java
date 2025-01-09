package com.techbridge.apis.repository;

import com.techbridge.apis.model.Message;
import com.techbridge.apis.model.Role;
import com.techbridge.apis.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value = "SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(@Param("name") String name);
}
