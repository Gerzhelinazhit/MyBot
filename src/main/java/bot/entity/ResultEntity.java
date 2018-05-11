package bot.entity;

import javax.persistence.*;

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

        if (idUser != that.idUser) return false;
        if (rightAnswers != null ? !rightAnswers.equals(that.rightAnswers) : that.rightAnswers != null) return false;
        if (wrongAnswers != null ? !wrongAnswers.equals(that.wrongAnswers) : that.wrongAnswers != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (rightAnswers != null ? rightAnswers.hashCode() : 0);
        result = 31 * result + (wrongAnswers != null ? wrongAnswers.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false)
    public UserEntity getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(UserEntity userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}
