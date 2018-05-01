package bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "cls_quest", schema = "bot", catalog = "")
public class ClsQuestEntity {
    private long id;
    private Long isDeleted;
    private String questText;

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
    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Basic
    @Column(name = "QUEST_TEXT", nullable = true, length = -1)
    public String getQuestText() {
        return questText;
    }

    public void setQuestText(String questText) {
        this.questText = questText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClsQuestEntity that = (ClsQuestEntity) o;

        if (id != that.id) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (questText != null ? !questText.equals(that.questText) : that.questText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + (questText != null ? questText.hashCode() : 0);
        return result;
    }
}
