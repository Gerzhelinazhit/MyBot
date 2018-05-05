package bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "cls_answer", schema = "bot", catalog = "")
public class ClsAnswerEntity {
    private long id;
    private Integer isDeleted;
    private String answerText;
    private String answerComment;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IS_DELETED", nullable = true)
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Basic
    @Column(name = "ANSWER_TEXT", nullable = true, length = 101)
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Basic
    @Column(name = "ANSWER_COMMENT", nullable = true, length = 101)
    public String getAnswerComment() {
        return answerComment;
    }

    public void setAnswerComment(String answerComment) {
        this.answerComment = answerComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClsAnswerEntity that = (ClsAnswerEntity) o;

        if (id != that.id) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (answerText != null ? !answerText.equals(that.answerText) : that.answerText != null) return false;
        if (answerComment != null ? !answerComment.equals(that.answerComment) : that.answerComment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + (answerComment != null ? answerComment.hashCode() : 0);
        return result;
    }
}
