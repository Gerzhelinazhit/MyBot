package bot.dao.impl;

import bot.dao.AbstractDao;
import bot.dao.NotesDao;
import bot.entity.NotesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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
    @Override
    public List<NotesEntity> getNotesByUser(int i){
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<NotesEntity> criteria = builder.createQuery(NotesEntity.class);
        Root<NotesEntity> notesRoot = criteria.from(NotesEntity.class);
        criteria.select(notesRoot);

        criteria.where(builder.equal(notesRoot.get("idUser"),i));

        return getEntityManager().createQuery(criteria).getResultList();
    }


}
