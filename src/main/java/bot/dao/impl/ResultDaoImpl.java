package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.ResultDao;
import bot.entity.NotesEntity;
import bot.entity.ResultEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ResultDaoImpl extends AbstractDao<Long, ResultEntity> implements ResultDao {
}