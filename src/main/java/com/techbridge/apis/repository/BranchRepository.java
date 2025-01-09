package com.techbridge.apis.repository;

import com.techbridge.apis.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch,Long> {
    @Query(value = "SELECT b FROM Branch b WHERE b.branchName = :name")
    Optional<Branch> findByName(@Param("name") String name);
}
