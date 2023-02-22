package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.model.Picture;
import com.serhiiostapenko.telegrambot.service.PictureService;
import com.serhiiostapenko.telegrambot.utils.CommandState;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class GetPictureCommand extends Command {
    private static final String REQUEST_MATCHER = "Get picture";
    private final PictureService pictureService;
    public GetPictureCommand(PictureService pictureService) {
        super(REQUEST_MATCHER);
        this.pictureService = pictureService;
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        Picture picture = pictureService.getRandPicture();

        userResponse.setChatID(userRequest.getChatId());
        userResponse.setPhoto(new InputFile(picture.getAttachName()));
        userResponse.setText(picture.getCaption());
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildMainMenu());

        userRequest.setCommandState(CommandState.WAITING_PICTURE);
        return userResponse;
    }

}