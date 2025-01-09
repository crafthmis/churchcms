package com.techbridge.apis.repository;

import com.techbridge.apis.model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CountyRepository extends JpaRepository<County,Long> {
    @Query(value = "SELECT c FROM County c WHERE c.name = :name")
    Optional<County> findByName(@Param("name") String name);

}
