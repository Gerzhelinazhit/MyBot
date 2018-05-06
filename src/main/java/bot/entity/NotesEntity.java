package bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "notes", schema = "bot", catalog = "")
public class NotesEntity {
    private int id;
    private String note;
    private int idUser;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NOTE", nullable = false, length = 101)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "ID_USER", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotesEntity that = (NotesEntity) o;

        if (id != that.id) return false;
        if (idUser != that.idUser) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + idUser;
        return result;
    }
}
