package bot.dao;

import bot.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface UserDao {
    UserEntity getByKey(Long key);
    void persist(UserEntity userEntity);
    void update(UserEntity userEntity);
    void delete(UserEntity userEntity);
    List getAll();
}
