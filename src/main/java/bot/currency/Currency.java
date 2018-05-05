package bot.currency;

import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("Cur_ID")
    private int Cur_ID;
    @SerializedName("Cur_Name")
    private String Cur_Name;
    @SerializedName("Cur_OfficialRate")
    private double Cur_OfficialRate;
    @SerializedName("Cur_Abbreviation")
    private String Cur_Abbreviation;
    @SerializedName("Date")
    private String Date;
    @SerializedName("Cur_Scale")
    private int Cur_Scale;

    public int getCur_ID() {
        return Cur_ID;
    }

    public void setCur_ID(int cur_ID) {
        Cur_ID = cur_ID;
    }

    public String getCur_Name() {
        return Cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        Cur_Name = cur_Name;
    }

    public double getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }

    public String getCur_Abbreviation() {
        return Cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        Cur_Abbreviation = cur_Abbreviation;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getCur_Scale() {
        return Cur_Scale;
    }

    public void setCur_Scale(int cur_Scale) {
        Cur_Scale = cur_Scale;
    }
}