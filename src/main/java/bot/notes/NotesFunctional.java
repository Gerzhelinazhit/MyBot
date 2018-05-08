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


    public void ClearNotes(Long key) {
        List<NotesEntity> notes = notesDao.getNotesByUser(key);
        System.out.println(notes.toString());
        for (NotesEntity item : notes) {
            System.out.println(item.getId() + item.getIdUser() + item.getNote());
            notesDao.delete(item);
        }
    }

    public String getNotes(Long key) {

        StringBuilder sb = new StringBuilder();
        List<NotesEntity> notes = notesDao.getNotesByUser(key);
        System.out.println(notes.toString());
        for (NotesEntity item : notes) {
            sb.append(item.getNote() + "\n");
        }
        return sb.toString();
    }

    public void addNotes(Long id,String note){
        NotesEntity notes = new NotesEntity();
        notes.setIdUser(Long.valueOf(id).intValue());
        notes.setNote(note);
        notesDao.persist(notes);
    }
}