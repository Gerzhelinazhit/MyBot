package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.UserDao;
import bot.entity.UserEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImpl extends AbstractDao<Long, UserEntity> implements UserDao {

        public List<UserEntity> getAll(){
        List list ;
        getSession().getTransaction().begin();
        list =getSession().createQuery("from UserEntity bot").getResultList();
        getSession().getTransaction().commit();
        return list;
    }


}
