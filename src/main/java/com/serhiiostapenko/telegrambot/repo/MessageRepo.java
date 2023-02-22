package com.serhiiostapenko.telegrambot.repo;

import com.serhiiostapenko.telegrambot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    @Query(value="SELECT * FROM message where is_checked = false ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Message> getRandomReason();
}
