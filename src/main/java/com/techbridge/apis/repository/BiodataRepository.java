package com.techbridge.apis.repository;

import com.techbridge.apis.model.Biodata;
import com.techbridge.apis.model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BiodataRepository extends JpaRepository<Biodata,Long> {
    @Query(value = "SELECT b FROM Biodata b WHERE b.idNumber = :idnumber")
    Optional<Biodata> findByIDNumber(@Param("idnumber") String idnumber);
}
