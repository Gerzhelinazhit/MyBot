package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.ClsAnswerDao;
import bot.entity.ClsAnswerEntity;

import java.util.List;

public class ClsAnswerDaoImpl extends AbstractDao<Long, ClsAnswerEntity> implements ClsAnswerDao {
    @Override
    public List<ClsAnswerEntity> getAll() {
        List list ;
    getSession().getTransaction().begin();
    list =getSession().createQuery("from ClsAnswerEntity bot").getResultList();
    getSession().getTransaction().commit();
        return list;
}
}
