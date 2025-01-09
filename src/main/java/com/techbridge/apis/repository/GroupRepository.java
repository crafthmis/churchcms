package com.techbridge.apis.repository;

import com.techbridge.apis.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepository  extends JpaRepository<Group,Long> {
    @Query(value = "SELECT g FROM Group g WHERE g.name = :name")
    Optional<Group> findByName(@Param("name") String name);
}
