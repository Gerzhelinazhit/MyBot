package bot.notes;

import bot.dao.NotesDao;
import bot.entity.NotesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotesFunctional {
    @Autowired
    NotesDao notesDao;

    public void deleteNotes() {
//        List<NotesEntity> forDelete = notesDao.getByKey();
//        for (NotesEntity item : forDelete) {
//            notesDao.delete(item);
//        }
    }

}