package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;

public class AdminCommand extends Command {
    private static final String REQUEST_MATCHER = "admin";
    public AdminCommand() {
        super(REQUEST_MATCHER);
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        userResponse.setChatID(userRequest.getChatId());
        userResponse.setText("Admin mode");
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildAdminMenu());
        return userResponse;
    }

}
