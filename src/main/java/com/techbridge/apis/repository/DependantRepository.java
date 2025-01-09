package com.techbridge.apis.repository;

import com.techbridge.apis.model.Campaign;
import com.techbridge.apis.model.Dependant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DependantRepository extends JpaRepository<Dependant,Long> {
    @Query(value = "SELECT c FROM Campaign c WHERE c.name = :name")
    Optional<Dependant> findByName(@Param("name") String name);
}
