package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.ClsQuestDao;
import bot.entity.ClsQuestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ClsQuestDaoImpl  extends AbstractDao<Long, ClsQuestEntity> implements ClsQuestDao {
    @Override
    public List <ClsQuestEntity> getAll() {
        List list ;

        list =getEntityManager().createQuery("from ClsQuestEntity bot").getResultList();
        return list;
    }
}
