package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;

public class GoBackCommand extends Command {
    private static final String REQUEST_MATCHER = "Go back";
    public GoBackCommand() {
        super(REQUEST_MATCHER);
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        userResponse.setChatID(userRequest.getChatId());
        userResponse.setText("Go back");
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildMainMenu());
        return userResponse;
    }

}