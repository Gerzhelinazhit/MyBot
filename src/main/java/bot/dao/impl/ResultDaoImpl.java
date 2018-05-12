package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.ResultDao;
import bot.entity.ResultEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ResultDaoImpl extends AbstractDao<Integer, ResultEntity> implements ResultDao {
    @Override
    public List<ResultEntity> getAll() {
        List list;

        list = getEntityManager().createQuery("from ResultEntity bot").getResultList();

        return list;
    }

}
