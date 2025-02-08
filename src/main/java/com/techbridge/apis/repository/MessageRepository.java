package com.techbridge.apis.repository;

import com.techbridge.apis.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query(value = "SELECT m FROM Message m WHERE m.hashmessage = :hash")
    Optional<Message> findByHash(@Param("hash") String hash);
}
