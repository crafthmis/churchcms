package com.techbridge.apis.repository;

import com.techbridge.apis.model.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiodataRepository extends JpaRepository<Biodata,Long> {
}
