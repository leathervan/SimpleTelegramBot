package com.serhiiostapenko.telegrambot.service;

import com.serhiiostapenko.telegrambot.model.Message;
import com.serhiiostapenko.telegrambot.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Transactional
    public Message save(Message message) {
        message.setIsChecked(false);
        return messageRepo.save(message);
    }

    @Transactional
    public Message getRandReason() {
        Message message;
        try {
            message = messageRepo.getRandomReason().orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            List<Message> messages = messageRepo.findAll();
            for (Message m : messages) m.setIsChecked(false);
            messageRepo.saveAll(messages);

            message = messageRepo.getRandomReason().get();
        }
        message.setIsChecked(true);
        return messageRepo.save(message);
    }
}
