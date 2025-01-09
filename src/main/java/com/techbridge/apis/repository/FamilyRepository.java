package com.techbridge.apis.repository;

import com.techbridge.apis.model.Family;
import com.techbridge.apis.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family,Long> {
    @Query(value = "SELECT f FROM Family f WHERE f.primaryMemId = :Id")
    Optional<Family> findByMemberId(@Param("Id") String Id);
}
