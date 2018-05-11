package bot.dao;

import bot.entity.ResultEntity;

public interface ResultDao {
    ResultEntity getByKey(Long key);
    void persist(ResultEntity resultEntity);
    void update(ResultEntity resultEntity);
    void delete(ResultEntity resultEntity);
    void flush ();
}
