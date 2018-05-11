package bot.notes;

import bot.dao.NotesDao;
import bot.entity.NotesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.joda.time.LocalDate;

import java.util.List;

@Repository
public class NotesFunctional {
    @Autowired
    NotesDao notesDao;

    private LocalDate localDate = new LocalDate();

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
            sb.append(item.getNote() + ". Была добавлена: "+item.getDate() +"\n");
        }
        return sb.toString();
    }

    public void addNotes(Long id,String note){
        localDate = LocalDate.now();
        NotesEntity notes = new NotesEntity();
        notes.setIdUser(Long.valueOf(id).intValue());
        notes.setNote(note);
        notes.setDate(localDate.toString());
        notesDao.persist(notes);
    }
}