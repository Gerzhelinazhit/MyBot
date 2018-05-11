package bot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "result", schema = "bot", catalog = "")
public class ResultEntity {
    private int idUser;
    private Integer rightAnswers;
    private Integer wrongAnswers;
    private UserEntity userByIdUser;

    @Id
    @Column(name = "ID_USER", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "RIGHT_ANSWERS", nullable = true)
    public Integer getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Integer rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    @Basic
    @Column(name = "WRONG_ANSWERS", nullable = true)
    public Integer getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(Integer wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultEntity that = (ResultEntity) o;
        return idUser == that.idUser &&
                Objects.equals(rightAnswers, that.rightAnswers) &&
                Objects.equals(wrongAnswers, that.wrongAnswers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, rightAnswers, wrongAnswers);
    }

    @OneToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false,insertable = false,updatable = false)
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}
