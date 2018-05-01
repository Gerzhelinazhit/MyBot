package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.ClsAnswerDao;
import bot.entity.ClsAnswerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository

public class ClsAnswerDaoImpl extends AbstractDao<Long, ClsAnswerEntity> implements ClsAnswerDao {

    @Override
    public List<ClsAnswerEntity> getAll() {
        List list ;
    list =getEntityManager().createQuery("from ClsAnswerEntity bot").getResultList();

        return list;
}
}
