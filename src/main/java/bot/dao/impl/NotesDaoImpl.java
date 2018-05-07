package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.NotesDao;
import bot.entity.NotesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class NotesDaoImpl extends AbstractDao<Long, NotesEntity> implements NotesDao{
    @Override
    public NotesEntity getByKey(int i){
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<NotesEntity> criteria = builder.createQuery(NotesEntity.class);
//        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<NotesEntity> userRoot = criteria.from(NotesEntity.class);
        NotesEntity notes = getEntityManager().find(NotesEntity.class, i);
        return notes;
    }


}
