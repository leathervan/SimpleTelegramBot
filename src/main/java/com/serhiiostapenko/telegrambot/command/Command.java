package com.serhiiostapenko.telegrambot.command;

import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;
import lombok.Data;

@Data
public abstract class Command {
    private String request;

    public Command(String request) {
        this.request = request;
    }

    abstract public UserResponse execute(UserRequest userRequest, UserResponse userResponse);

    public boolean matches(UserRequest userRequest){return request.matches(userRequest.getText());
    }
}
