package com.techbridge.apis.repository;

import com.techbridge.apis.model.CampaignGroup;
import com.techbridge.apis.model.Sermon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SermonRepository extends JpaRepository<Sermon,Long> {
}
