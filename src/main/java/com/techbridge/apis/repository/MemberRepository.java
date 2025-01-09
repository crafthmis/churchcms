package com.techbridge.apis.repository;

import com.techbridge.apis.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
