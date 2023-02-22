package com.serhiiostapenko.telegrambot;

import com.serhiiostapenko.telegrambot.command.CommandHandler;
import com.serhiiostapenko.telegrambot.utils.CommandState;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;
    private final CommandHandler commandHandler;
    private UserRequest userRequest;

    @Autowired
    public Bot(CommandHandler commandHandler, UserRequest userRequest) {
        this.commandHandler = commandHandler;
        this.userRequest = userRequest;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            UserResponse userResponse = new UserResponse();

            userRequest.setChatId(update.getMessage().getChatId());

            if (update.getMessage().hasText()) {
                userRequest.setText(update.getMessage().getText());

                commandHandler.getCommand(userRequest).execute(userRequest, userResponse);

                if(userRequest.getCommandState().equals(CommandState.WAITING_PICTURE)) {
                    sendPhoto(userResponse);
                    userRequest.setCommandState(CommandState.DEFAULT_CONVERSATION);
                }
                else sendMessage(userResponse);
            } else if (update.getMessage().hasPhoto() && userRequest.getCommandState().equals(CommandState.SENDING_PICTURE)) {
                userRequest.setText(update.getMessage().getCaption() != null ? update.getMessage().getCaption() : "");
                userRequest.setPhotos(update.getMessage().getPhoto());

                commandHandler.getCommand(userRequest).execute(userRequest, userResponse);

                sendPhoto(userResponse);
            } else sendErrorMessage();

        }
    }

    private void sendMessage(UserResponse userResponse) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userResponse.getChatID());
        sendMessage.setText(userResponse.getText());
        sendMessage.setReplyMarkup(userResponse.getReplyKeyboardMarkup());
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            sendErrorMessage();
        }
    }

    private void sendPhoto(UserResponse userResponse) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(userResponse.getChatID());
        sendPhoto.setPhoto(userResponse.getPhoto());
        sendPhoto.setCaption(userResponse.getText());
        sendPhoto.setReplyMarkup(userResponse.getReplyKeyboardMarkup());
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
            sendErrorMessage();
        }
    }

    private void sendErrorMessage() {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(userRequest.getChatId());
        sendMessage.setText("Something went wrong");
        sendMessage.setReplyMarkup(KeyBoardBuilder.buildMainMenu());
        userRequest.setCommandState(CommandState.DEFAULT_CONVERSATION);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
