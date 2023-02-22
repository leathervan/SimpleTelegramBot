package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.service.MessageService;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;

public class GetMessageCommand extends Command {
    private static final String REQUEST_MATCHER = "Get message";

    private final MessageService messageService;
    public GetMessageCommand(MessageService messageService) {
        super(REQUEST_MATCHER);
        this.messageService = messageService;
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        userResponse.setChatID(userRequest.getChatId());
        userResponse.setText(messageService.getRandReason().getText());
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildMainMenu());
        return userResponse;
    }

}
