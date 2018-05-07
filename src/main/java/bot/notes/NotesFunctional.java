package bot.notes;

import bot.dao.NotesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotesFunctional {
    @Autowired
    NotesDao notesDao;


    public void getNotesforDelete() {
List<List> notes  = notesDao.getNotesByUser(131416810);
notes.toString();
    }

}