package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.ClsQuestDao;
import bot.entity.ClsQuestEntity;

import java.util.List;

public class ClsQuestDaoImpl  extends AbstractDao<Long, ClsQuestEntity> implements ClsQuestDao {
    @Override
    public List <ClsQuestEntity> getAll() {
        List list ;
        getSession().getTransaction().begin();
        list =getSession().createQuery("from ClsQuestEntity bot").getResultList();
        getSession().getTransaction().commit();
        return list;
    }
}
