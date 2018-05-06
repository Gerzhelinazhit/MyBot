package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.CurrencyDao;
import bot.entity.CurrencyEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class CurrencyDaoImpl extends AbstractDao<Integer, CurrencyEntity> implements CurrencyDao {
    @Override
    public List<CurrencyEntity> getAll() {
        List list;
        list = getEntityManager().createQuery("from CurrencyEntity bot").getResultList();

        return list;
    }
    @Override
    public CurrencyEntity getByKey(int i){
//        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        CurrencyEntity currency = getEntityManager().find(CurrencyEntity.class, i);
        return currency;
    }

}
