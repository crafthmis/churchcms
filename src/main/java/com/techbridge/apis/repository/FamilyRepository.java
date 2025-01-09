package com.techbridge.apis.repository;

import com.techbridge.apis.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family,Long> {
}
