package bot.dao;

import bot.entity.UserEntity;

import java.util.List;


public interface UserDao {
    UserEntity getByKey(Long key);
    void persist(UserEntity userEntity);
    void update(UserEntity userEntity);
    void delete(UserEntity userEntity);
    void flush ();
    List getAll();
}
