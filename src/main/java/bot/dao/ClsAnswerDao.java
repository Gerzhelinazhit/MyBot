package bot.dao;

import bot.entity.ClsAnswerEntity;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ClsAnswerDao {
    ClsAnswerEntity getByKey(Long key);
    void persist(ClsAnswerEntity clsAnswerEntity);
    void update(ClsAnswerEntity clsAnswerEntity);
    void delete(ClsAnswerEntity clsAnswerEntity);
    void flush ();
    List getAll();

}
