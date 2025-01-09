package com.techbridge.apis.repository;

import com.techbridge.apis.model.MemberBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDependantRepository extends JpaRepository<MemberBranch,Long> {
}
