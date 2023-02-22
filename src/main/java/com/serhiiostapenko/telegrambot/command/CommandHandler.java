package com.serhiiostapenko.telegrambot.command;

import com.serhiiostapenko.telegrambot.command.commands.*;
import com.serhiiostapenko.telegrambot.service.MessageService;
import com.serhiiostapenko.telegrambot.service.PictureService;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandHandler {
    private final MessageService messageService;
    private final PictureService pictureService;
    private final List<Command> commands = new ArrayList<>();

    CommandHandler(MessageService messageService, PictureService pictureService) {
        this.messageService = messageService;
        this.pictureService = pictureService;

        commands.add(new StartCommand());
        commands.add(new GetPictureCommand(pictureService));
        commands.add(new GetMessageCommand(messageService));
        commands.add(new AddPictureRequestCommand());
        commands.add(new AddMessageRequestCommand());
        commands.add(new AddPictureCommand(pictureService));
        commands.add(new AddMessageCommand(messageService));
        commands.add(new AdminCommand());
        commands.add(new GoBackCommand());
    }

    public Command getCommand(UserRequest userRequest) {
        for (Command c : commands) {
            if (c.matches(userRequest)) return c;
        }
        return incorrectRequestResponse();
    }

    private Command incorrectRequestResponse() {
        return new Command("") {
            @Override
            public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
                userResponse.setChatID(userRequest.getChatId());
                userResponse.setText("Something went wrong");
                return userResponse;
            }
        };
    }
}
