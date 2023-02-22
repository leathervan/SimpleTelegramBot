package com.serhiiostapenko.telegrambot.utils;

import lombok.Data;
import lombok.ToString;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

@Data
@ToString
public class UserResponse {
    private Long chatID;
    private String text;
    private ReplyKeyboardMarkup replyKeyboardMarkup;
    private InputFile photo;
}
