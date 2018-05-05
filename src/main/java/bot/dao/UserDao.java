package bot.dao;

import bot.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserDao {
    UserEntity getByKey(Long key);
    void persist(UserEntity userEntity);
    void update(UserEntity userEntity);
    void delete(UserEntity userEntity);
    List getAll();
}
