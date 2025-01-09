package com.techbridge.apis.repository;

import com.techbridge.apis.model.CampaignGroup;
import com.techbridge.apis.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SermonRepository extends JpaRepository<Sermon,Long> {
    @Query(value = "SELECT s FROM Sermon s WHERE s.title = :title")
    Optional<Sermon> findByTitle(@Param("title") String title);
}
