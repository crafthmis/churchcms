package com.techbridge.apis.repository;

import com.techbridge.apis.model.CampaignGroup;
import com.techbridge.apis.model.Dependant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependantRepository extends JpaRepository<Dependant,Long> {
}
