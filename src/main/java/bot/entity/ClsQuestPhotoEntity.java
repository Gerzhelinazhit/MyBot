package bot.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "cls_quest_photo", schema = "bot", catalog = "")
public class ClsQuestPhotoEntity {
    private long id;
    private Integer isDeleted;
    private byte[] relFilePath;
    private byte[] photoText;
    private ClsQuestEntity clsQuestByIdQuest;

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
    @Column(name = "REL_FILE_PATH", nullable = true)
    public byte[] getRelFilePath() {
        return relFilePath;
    }

    public void setRelFilePath(byte[] relFilePath) {
        this.relFilePath = relFilePath;
    }

    @Basic
    @Column(name = "PHOTO_TEXT", nullable = true)
    public byte[] getPhotoText() {
        return photoText;
    }

    public void setPhotoText(byte[] photoText) {
        this.photoText = photoText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClsQuestPhotoEntity that = (ClsQuestPhotoEntity) o;

        if (id != that.id) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        if (!Arrays.equals(relFilePath, that.relFilePath)) return false;
        if (!Arrays.equals(photoText, that.photoText)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(relFilePath);
        result = 31 * result + Arrays.hashCode(photoText);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "ID_QUEST", referencedColumnName = "ID", nullable = false)
    public ClsQuestEntity getClsQuestByIdQuest() {
        return clsQuestByIdQuest;
    }

    public void setClsQuestByIdQuest(ClsQuestEntity clsQuestByIdQuest) {
        this.clsQuestByIdQuest = clsQuestByIdQuest;
    }
}
