package bot.dao;

import bot.entity.ClsAnswerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClsAnswerDao {
    ClsAnswerEntity getByKey(Long key);
    void persist(ClsAnswerEntity clsAnswerEntity);
    void update(ClsAnswerEntity clsAnswerEntity);
    void delete(ClsAnswerEntity clsAnswerEntity);
    List getAll();
}
