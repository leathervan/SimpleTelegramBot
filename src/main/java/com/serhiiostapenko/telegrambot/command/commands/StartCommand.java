package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;


public class StartCommand extends Command {
    private static final String REQUEST_MATCHER = "/start";
    public StartCommand() {
        super(REQUEST_MATCHER);
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        userResponse.setChatID(userRequest.getChatId());
        userResponse.setText("Start message");
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildMainMenu());
        return userResponse;
    }

}
