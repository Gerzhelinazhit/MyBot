package bot.converter;

import bot.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.User;

public class UserConverter {

    public UserEntity getUserInfo(User userInfo) {
        UserEntity user = new UserEntity();
        user.setId(userInfo.getId());
        user.setFirstName(userInfo.getFirstName());
        user.setIsBot(Boolean.toString(userInfo.getBot()));
        user.setLanguageCode(userInfo.getLanguageCode());
        user.setLastName(userInfo.getLastName());
        user.setUserName(userInfo.getUserName());
        return user;
    }
}
