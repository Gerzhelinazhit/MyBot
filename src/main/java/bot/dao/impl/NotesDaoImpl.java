package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.NotesDao;
import bot.entity.NotesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NotesDaoImpl extends AbstractDao<Long, NotesEntity> implements NotesDao{

}
