package com.serhiiostapenko.telegrambot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.List;

public class KeyBoardBuilder {
    public static ReplyKeyboardMarkup buildMainMenu(){
        List<KeyboardButton> buttons = Arrays.asList(
                new KeyboardButton("Get message"),
                new KeyboardButton("Get picture"));
        KeyboardRow row1 = new KeyboardRow(buttons);

        return ReplyKeyboardMarkup.builder()
                .keyboard(Arrays.asList(row1))
                .selective(true)
                .resizeKeyboard(true)
                .build();
    }

    public static ReplyKeyboardMarkup buildAdminMenu(){
        List<KeyboardButton> buttons = Arrays.asList(
                new KeyboardButton("Add message"),
                new KeyboardButton("Add picture"));
        KeyboardRow row1 = new KeyboardRow(buttons);
        KeyboardRow row2 = new KeyboardRow(Arrays.asList(new KeyboardButton("Go back")));

        return ReplyKeyboardMarkup.builder()
                .keyboard(Arrays.asList(row1,row2))
                .selective(true)
                .resizeKeyboard(true)
                .build();
    }
}
