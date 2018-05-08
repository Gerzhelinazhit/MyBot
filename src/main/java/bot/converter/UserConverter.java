package bot.converter;

import bot.dao.UserDao;
import bot.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.User;

@Component
public class UserConverter {
    @Autowired
    private UserDao userDao;

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
    public void deleteUser(User user){

     UserEntity userId  = new UserEntity();
     userDao.getByKey(Long.valueOf(user.getId()));
    }
}
