package bot.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "bot", catalog = "")
public class UserEntity {
    private int id;
    private String firstName;
    private String isBot;
    private String languageCode;
    private String lastName;
    private String userName;
    private Collection<NotesEntity> notesById;
    private ResultEntity resultById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "IS_BOT", nullable = true, length = 45)
    public String getIsBot() {
        return isBot;
    }

    public void setIsBot(String isBot) {
        this.isBot = isBot;
    }

    @Basic
    @Column(name = "LANGUAGE_CODE", nullable = true, length = 45)
    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Basic
    @Column(name = "LAST_NAME", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(isBot, that.isBot) &&
                Objects.equals(languageCode, that.languageCode) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, isBot, languageCode, lastName, userName);
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<NotesEntity> getNotesById() {
        return notesById;
    }

    public void setNotesById(Collection<NotesEntity> notesById) {
        this.notesById = notesById;
    }

    @OneToOne(mappedBy = "userByIdUser")
    public ResultEntity getResultById() {
        return resultById;
    }

    public void setResultById(ResultEntity resultById) {
        this.resultById = resultById;
    }
}
