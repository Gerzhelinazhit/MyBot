package bot.dao;

import bot.entity.ClsQuestEntity;

import java.util.List;


public interface ClsQuestDao {
    ClsQuestEntity getByKey(Long key);
    void persist(ClsQuestEntity clsAnswerEntity);
    void update(ClsQuestEntity clsAnswerEntity);
    void delete(ClsQuestEntity clsAnswerEntity);
    void flush ();
    List getAll();
}
