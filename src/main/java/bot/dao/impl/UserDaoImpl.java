package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.UserDao;
import bot.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Long, UserEntity> implements UserDao {

        @Override
        public List<UserEntity> getAll() {
                List list;

                list = getEntityManager().createQuery("from UserEntity bot").getResultList();

                return list;
        }


}
