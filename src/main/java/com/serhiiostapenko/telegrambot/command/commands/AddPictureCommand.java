package com.serhiiostapenko.telegrambot.command.commands;

import com.serhiiostapenko.telegrambot.command.Command;
import com.serhiiostapenko.telegrambot.model.Picture;
import com.serhiiostapenko.telegrambot.service.PictureService;
import com.serhiiostapenko.telegrambot.utils.CommandState;
import com.serhiiostapenko.telegrambot.utils.KeyBoardBuilder;
import com.serhiiostapenko.telegrambot.utils.UserRequest;
import com.serhiiostapenko.telegrambot.utils.UserResponse;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class AddPictureCommand extends Command {
    private final PictureService pictureService;
    public AddPictureCommand(PictureService pictureService) {
        super("");
        this.pictureService = pictureService;
    }

    @Override
    public boolean matches(UserRequest userRequest) {
        return userRequest.getCommandState().equals(CommandState.SENDING_PICTURE);
    }

    public UserResponse execute(UserRequest userRequest, UserResponse userResponse) {
        InputFile photo = new InputFile(userRequest.getPhotos().get(0).getFileId());

        Picture picture = new Picture();
        picture.setAttachName(photo.getAttachName());
        picture.setCaption(userRequest.getText());
        picture.setIsChecked(false);
        pictureService.save(picture);

        userResponse.setChatID(userRequest.getChatId());
        userResponse.setPhoto(photo);
        userResponse.setText(userRequest.getText() + "\n\nPicture was added");
        userResponse.setReplyKeyboardMarkup(KeyBoardBuilder.buildAdminMenu());
        userRequest.setCommandState(CommandState.DEFAULT_CONVERSATION);

        return userResponse;
    }

}
