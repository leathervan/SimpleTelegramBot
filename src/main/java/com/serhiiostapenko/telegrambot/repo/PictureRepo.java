package com.serhiiostapenko.telegrambot.repo;

import com.serhiiostapenko.telegrambot.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PictureRepo extends JpaRepository<Picture, Long> {
    @Query(value="SELECT * FROM picture where is_checked = false ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Picture> getRandomPicture();
}
