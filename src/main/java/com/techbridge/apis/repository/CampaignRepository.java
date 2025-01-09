package com.techbridge.apis.repository;

import com.techbridge.apis.model.Branch;
import com.techbridge.apis.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    @Query(value = "SELECT c FROM Campaign c WHERE c.name = :name")
    Optional<Campaign> findByName(@Param("name") String name);
}
