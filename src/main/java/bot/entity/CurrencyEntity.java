package bot.entity;

import javax.persistence.*;

@Entity
@Table(name = "currency", schema = "bot", catalog = "")
public class CurrencyEntity {
    private int id;
    private String curName;
    private double curOfficialRate;
    private String curAbbreviation;
    private int curScale;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Cur_Name", nullable = false, length = 45)
    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    @Basic
    @Column(name = "Cur_OfficialRate", nullable = false, precision = 0)
    public double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurOfficialRate(double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }

    @Basic
    @Column(name = "Cur_Abbreviation", nullable = false, length = 45)
    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    @Basic
    @Column(name = "Cur_Scale", nullable = false)
    public int getCurScale() {
        return curScale;
    }

    public void setCurScale(int curScale) {
        this.curScale = curScale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyEntity that = (CurrencyEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.curOfficialRate, curOfficialRate) != 0) return false;
        if (curScale != that.curScale) return false;
        if (curName != null ? !curName.equals(that.curName) : that.curName != null) return false;
        if (curAbbreviation != null ? !curAbbreviation.equals(that.curAbbreviation) : that.curAbbreviation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (curName != null ? curName.hashCode() : 0);
        temp = Double.doubleToLongBits(curOfficialRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (curAbbreviation != null ? curAbbreviation.hashCode() : 0);
        result = 31 * result + curScale;
        return result;
    }
}
