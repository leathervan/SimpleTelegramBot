package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.utils.CommandState;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;

public class AddPictureRequestCommand extends Command {
    private static final String REQUEST_MATCHER = "Add picture";
    public AddPictureRequestCommand() {
        super(REQUEST_MATCHER);
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        userResponse.setChatID(userRequest.getChatId());
        userResponse.setText("Send picture");
        userRequest.setCommandState(CommandState.SENDING_PICTURE);
        return userResponse;
    }

}
