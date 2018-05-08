package bot.dao;

import bot.entity.CurrencyEntity;

import java.util.List;

public interface CurrencyDao {
    CurrencyEntity getByKey(Integer key);
    void persist(CurrencyEntity currencyEntity);
    void update(CurrencyEntity currencyEntity);
    void delete(CurrencyEntity currencyEntity);
    void flush ();
    List getAll();
    CurrencyEntity getByKey(int i);
}
