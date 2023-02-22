package com.serhiiostapenko.telegrambot.service;

import com.serhiiostapenko.telegrambot.model.Picture;
import com.serhiiostapenko.telegrambot.repo.PictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PictureService {
    private final PictureRepo pictureRepo;

    @Autowired
    public PictureService(PictureRepo pictureRepo) {
        this.pictureRepo = pictureRepo;
    }

    @Transactional
    public Picture save(Picture picture) {
        picture.setIsChecked(false);
        return pictureRepo.save(picture);
    }

    @Transactional
    public Picture getRandPicture() {
        Picture picture;
        try {
            picture = pictureRepo.getRandomPicture().orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            List<Picture> pictures = pictureRepo.findAll();
            for (Picture p : pictures) p.setIsChecked(false);
            pictureRepo.saveAll(pictures);

            picture = pictureRepo.getRandomPicture().get();
        }
        picture.setIsChecked(true);
        return pictureRepo.save(picture);
    }
}
