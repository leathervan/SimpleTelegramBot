package com.serhiiostapenko.telegrambot.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class UserRequest {
    Long chatId;
    String text;
    List<PhotoSize> photos = new ArrayList<>();
    CommandState commandState = CommandState.DEFAULT_CONVERSATION;
}
