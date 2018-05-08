package bot.dao;

import bot.entity.NotesEntity;

import java.util.List;

public interface NotesDao {
    NotesEntity getByKey(Long key);
    void persist(NotesEntity notesEntity);
    void update(NotesEntity notesEntity);
    void delete(NotesEntity notesEntity);
    NotesEntity getByKey(int i);
    List getNotesByUser(Long key);
}
