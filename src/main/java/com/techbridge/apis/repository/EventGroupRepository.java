package com.techbridge.apis.repository;

import com.techbridge.apis.model.EventGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventGroupRepository  extends JpaRepository<EventGroup,Long> {
}
