package bot.dao;

import bot.entity.ResultEntity;

import java.util.List;

public interface ResultDao {
    ResultEntity getByKey(Integer key);
    void persist(ResultEntity resultEntity);
    void update(ResultEntity resultEntity);
    void delete(ResultEntity resultEntity);
    void flush ();
    List getAll();
}
