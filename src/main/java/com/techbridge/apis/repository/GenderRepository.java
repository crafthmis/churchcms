package com.techbridge.apis.repository;

import com.techbridge.apis.model.County;
import com.techbridge.apis.model.EventGroup;
import com.techbridge.apis.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GenderRepository  extends JpaRepository<Gender,Long> {
    @Query(value = "SELECT g FROM Gender g WHERE g.name = :name")
    Optional<Gender> findByName(@Param("name") String name);
}
