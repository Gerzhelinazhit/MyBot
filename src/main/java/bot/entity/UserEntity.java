package bot.entity;

import javax.persistence.*;
import java.util.Collection;

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

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (isBot != null ? !isBot.equals(that.isBot) : that.isBot != null) return false;
        if (languageCode != null ? !languageCode.equals(that.languageCode) : that.languageCode != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (isBot != null ? isBot.hashCode() : 0);
        result = 31 * result + (languageCode != null ? languageCode.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByIdUser")
    public Collection<NotesEntity> getNotesById() {
        return notesById;
    }

    public void setNotesById(Collection<NotesEntity> notesById) {
        this.notesById = notesById;
    }
}
