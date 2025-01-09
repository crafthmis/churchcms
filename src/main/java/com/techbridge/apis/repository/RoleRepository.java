package com.techbridge.apis.repository;

import com.techbridge.apis.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
