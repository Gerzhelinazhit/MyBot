package bot.converter;

import bot.dao.UserDao;
import bot.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.telegram.telegrambots.api.objects.User;

public class UserConverter {
    ApplicationContext context  = new FileSystemXmlApplicationContext("./resources/application-context.xml");

    UserEntity user = (UserEntity) context.getBean("user") ;

    public UserEntity getUserInfo(User userInfo){

        user.setId(userInfo.getId());
        user.setFirstName(userInfo.getFirstName());
        user.setIsBot(Boolean.toString(userInfo.getBot()));
        user.setLanguageCode(userInfo.getLanguageCode());
        user.setLastName(userInfo.getLastName());
        user.setUserName(userInfo.getUserName());
        return user;
    }
}
