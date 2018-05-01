package bot.dao;

import bot.entity.ClsQuestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClsQuestDao {
    ClsQuestEntity getByKey(Long key);
    void persist(ClsQuestEntity clsAnswerEntity);
    void update(ClsQuestEntity clsAnswerEntity);
    void delete(ClsQuestEntity clsAnswerEntity);
    List getAll();
}
