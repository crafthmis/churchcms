package com.techbridge.apis.repository;

import com.techbridge.apis.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "SELECT e FROM Event e WHERE e.name = :name")
    Optional<Event> findByName(@Param("name") String name);
}
