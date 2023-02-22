package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.model.Message;
import com.serhiiostapenko.telegrambot.service.MessageService;
import com.serhiiostapenko.telegrambot.utils.CommandState;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;

public class AddMessageCommand extends Command {
    private final MessageService messageService;
    public AddMessageCommand(MessageService messageService) {
        super("");
        this.messageService = messageService;
    }

    @Override
    public boolean matches(UserRequest userRequest) {
        return userRequest.getCommandState().equals(CommandState.SENDING_MESSAGE);
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        Message message = new Message();
        message.setText(userRequest.getText());
        messageService.save(message);

        userResponse.setChatID(userRequest.getChatId());
        userResponse.setText("Message: " + userRequest.getText() +  " was added");
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildAdminMenu());

        userRequest.setCommandState(CommandState.DEFAULT_CONVERSATION);
        return userResponse;
    }

}
